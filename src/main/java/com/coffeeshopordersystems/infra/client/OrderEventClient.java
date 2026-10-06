package com.coffeeshopordersystems.infra.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@Slf4j
public class OrderEventClient {
    private final RestClient restClient = RestClient.create("https://httpbin.org");

    public void send(Long userId, List<Long> menuIds, long totalPrice){
        try{
            restClient.post()
                    .uri("/post")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new OrderEventRequest(userId,menuIds,totalPrice))
                    .retrieve()
                    .toBodilessEntity();
        } catch(Exception e){
            log.warn("데이터 수집 플랫폼 전송 실패: {}", e.getMessage());
        }
    }
    private record OrderEventRequest(Long userId, List<Long> menuIds, long totalPrice){}
}
