package com.coffeeshopordersystems.domain.order.repository;

import com.coffeeshopordersystems.domain.order.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
