package com.coffeeshopordersystems.domain.order.repository;

public interface PopularMenuProjection {
    Long getMenuId();
    String getName();
    Long getOrderCount();
}
