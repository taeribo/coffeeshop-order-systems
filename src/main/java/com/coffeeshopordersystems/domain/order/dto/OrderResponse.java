package com.coffeeshopordersystems.domain.order.dto;

import java.util.List;

public record OrderResponse(Long orderId, String status, long totalPrice, long remainingPoint, List<OrderItemResponse> items) {
    public record OrderItemResponse(Long menuId, String name, int quantity, long price){}
}
