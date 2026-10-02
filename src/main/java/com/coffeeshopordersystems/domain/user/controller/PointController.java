package com.coffeeshopordersystems.domain.user.controller;

import com.coffeeshopordersystems.domain.user.dto.PointChargeRequest;
import com.coffeeshopordersystems.domain.user.dto.PointChargeResponse;
import com.coffeeshopordersystems.domain.user.service.PointService;
import com.coffeeshopordersystems.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PointController {

    private final PointService pointService;

    @PostMapping("/points/charge")
    public ApiResponse<PointChargeResponse> charge(@RequestBody PointChargeRequest request){
        long point = pointService.charge(request.userId(), request.amount());
        PointChargeResponse response = new PointChargeResponse(request.userId(), point);
        return ApiResponse.success("포인트 충전에 성공했습니다.", response);
    }
}
