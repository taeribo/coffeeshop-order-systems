package com.coffeeshopordersystems.domain.order.entity;

import com.coffeeshopordersystems.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "order_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "menu_id", nullable = false)
    private Long menuId;

    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    @Column(name ="product_price", nullable = false)
    private long productPrice;

    @Column(nullable = false)
    private int quantity;

    public OrderItem(Long orderId, Long menuId, String productName, long productPrice, int quantity){
        this.orderId = orderId;
        this.menuId = menuId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
    }

    public long getLineTotal(){
        return productPrice * quantity;
    }
}
