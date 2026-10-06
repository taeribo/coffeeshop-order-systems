package com.coffeeshopordersystems.domain.order.service;

import com.coffeeshopordersystems.domain.order.dto.OrderRequest;
import com.coffeeshopordersystems.domain.user.entity.User;
import com.coffeeshopordersystems.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderConcurrencyTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void 동시에_여러_주문이_들어와도_잔액_초과로_차감되지_않는다() throws InterruptedException {
        Long testUserId = 999L;
        long menuPrice = 4500L;
        long chargeAmount = menuPrice * 3; // 3번 주문 가능한 잔액

        User user = userRepository.save(new User(testUserId));
        user.charge(chargeAmount);
        userRepository.save(user);

        int threadCount = 5;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);
        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        OrderRequest request = new OrderRequest(
                testUserId,
                List.of(new OrderRequest.OrderItemRequest(1L, 1))
        );

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    orderService.order(request);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    failCount.incrementAndGet();
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        executor.shutdown();

        User result = userRepository.findById(testUserId).orElseThrow();

        assertThat(successCount.get() + failCount.get()).isEqualTo(threadCount);
        assertThat(successCount.get()).isLessThanOrEqualTo(3);
        long expectedRemaining = chargeAmount - (successCount.get() * menuPrice);
        assertThat(result.getPoint()).isEqualTo(expectedRemaining);
        assertThat(result.getPoint()).isGreaterThanOrEqualTo(0);
    }

}
