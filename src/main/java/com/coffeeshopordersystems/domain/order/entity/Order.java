package com.coffeeshopordersystems.domain.order.entity;

import com.coffeeshopordersystems.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name="user_id", nullable = false)
    private Long userId;

    @Column(name = "total_price", nullable = false)
    private long totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus orderStatus;

    @Column(name = "cancelled_at")
    private LocalDateTime cancelledAt;

    public Order(Long userId, long totalPrice){
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.orderStatus = OrderStatus.PENDING;
    }

    public void pay(){
        this.orderStatus = OrderStatus.PAID;
    }

    public void fail(){
        this.orderStatus = OrderStatus.FAILED;
    }
}
