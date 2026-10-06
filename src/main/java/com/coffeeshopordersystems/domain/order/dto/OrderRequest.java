package com.coffeeshopordersystems.domain.order.dto;

import java.util.List;

public record OrderRequest(Long userId, List<OrderItemRequest> items){
    public record OrderItemRequest(Long menuId, int quantity){}
}
