package com.coffeeshopordersystems.domain.order.controller;

import com.coffeeshopordersystems.domain.order.dto.OrderRequest;
import com.coffeeshopordersystems.domain.order.dto.OrderResponse;
import com.coffeeshopordersystems.domain.order.service.OrderService;
import com.coffeeshopordersystems.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<OrderResponse> order(@RequestBody OrderRequest request){
        OrderResponse response = orderService.order(request);
        return ApiResponse.success("주문 및 결제에 성공했습니다.", response);
    }
}
