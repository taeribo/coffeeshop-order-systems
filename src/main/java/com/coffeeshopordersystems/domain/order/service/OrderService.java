package com.coffeeshopordersystems.domain.order.service;

import com.coffeeshopordersystems.domain.menu.entity.Menu;
import com.coffeeshopordersystems.domain.menu.repository.MenuRepository;
import com.coffeeshopordersystems.domain.order.dto.OrderRequest;
import com.coffeeshopordersystems.domain.order.dto.OrderResponse;
import com.coffeeshopordersystems.domain.order.entity.Order;
import com.coffeeshopordersystems.domain.order.entity.OrderItem;
import com.coffeeshopordersystems.domain.order.repository.OrderItemRepository;
import com.coffeeshopordersystems.domain.order.repository.OrderRepository;
import com.coffeeshopordersystems.domain.payment.entity.Payment;
import com.coffeeshopordersystems.domain.payment.repository.PaymentRepository;
import com.coffeeshopordersystems.domain.user.entity.User;
import com.coffeeshopordersystems.domain.user.repository.UserRepository;
import com.coffeeshopordersystems.global.exception.BusinessException;
import com.coffeeshopordersystems.global.exception.ErrorCode;
import com.coffeeshopordersystems.infra.client.OrderEventClient;
import com.coffeeshopordersystems.infra.lock.RedisLockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderCoreService orderCoreService;
  private final RedisLockService redisLockService;
  private final OrderEventClient orderEventClient;

    public OrderResponse order(OrderRequest request) {
        String lockKey = "lock:point:" + request.userId();
        String lockValue = UUID.randomUUID().toString();

        boolean locked = redisLockService.tryLock(lockKey, lockValue, 5);
        if (!locked) {
            throw new BusinessException(ErrorCode.ORDER_PROCESSING);
        }

        try {
            OrderResponse response = orderCoreService.processOrder(request);

            List<Long> menuIds = request.items().stream()
                    .map(OrderRequest.OrderItemRequest::menuId)
                    .toList();
            orderEventClient.send(request.userId(), menuIds, response.totalPrice());

            return response;
        } finally {
            redisLockService.unlock(lockKey, lockValue);
        }
    }
}

