package com.coffeeshopordersystems.domain.order.repository;

import com.coffeeshopordersystems.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
