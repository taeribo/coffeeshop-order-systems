package com.coffeeshopordersystems.domain.user.controller;

import com.coffeeshopordersystems.domain.user.dto.PointChargeRequest;
import com.coffeeshopordersystems.domain.user.dto.PointChargeResponse;
import com.coffeeshopordersystems.domain.user.service.PointService;
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
    public PointChargeResponse charge(@RequestBody PointChargeRequest request){
        long point = pointService.charge(request.userId(), request.amount());
        return new PointChargeResponse(request.userId(), point);
    }
}
