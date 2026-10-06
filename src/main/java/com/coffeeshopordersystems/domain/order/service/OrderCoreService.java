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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCoreService {

    private final UserRepository userRepository;
    private final MenuRepository menuRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Transactional
    public OrderResponse processOrder(OrderRequest request){
        User user = userRepository.findById(request.userId())
                .orElseGet(() -> userRepository.save(new User(request.userId())));

        List<Menu> menus = new ArrayList<>();
        long totalPrice = 0;
        for(OrderRequest.OrderItemRequest item : request.items()){
            Menu menu = menuRepository.findById(item.menuId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.MENU_NOT_FOUND));
            menus.add(menu);
            totalPrice += menu.getPrice() * item.quantity();
        }

        Order order = orderRepository.save(new Order(user.getId(), totalPrice));

        List<OrderResponse.OrderItemResponse> itemResponses = new ArrayList<>();
        for(int i = 0; i < request.items().size(); i++){
            OrderRequest.OrderItemRequest itemRequest = request.items().get(i);
            Menu menu = menus.get(i);

            orderItemRepository.save(new OrderItem(
                    order.getId(), menu.getId(), menu.getName(), menu.getPrice(), itemRequest.quantity()
            ));

            itemResponses.add(new OrderResponse.OrderItemResponse(
                    menu.getId(), menu.getName(), itemRequest.quantity(), menu.getPrice()
            ));
        }
        Payment payment = new Payment(order.getId(), totalPrice);

        try{
            user.use(totalPrice);
            userRepository.save(user);
        } catch (BusinessException e){
            order.fail();
            payment.fail();
            paymentRepository.save(payment);
            throw e;
        }

        order.pay();
        payment.success();
        paymentRepository.save(payment);

        return new OrderResponse(
                order.getId(), order.getOrderStatus().name(), totalPrice, user.getPoint(), itemResponses
        );
    }
}
