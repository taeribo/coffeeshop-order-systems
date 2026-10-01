package com.coffeeshopordersystems.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  INVALID_REQUEST(HttpStatus.BAD_REQUEST,"COMMON_001", "요청형식이 올바르지 않습니다."),
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_002", "서버 오류가 발생했습니다."),

  CHARGE_AMOUNT_INVALID(HttpStatus.BAD_REQUEST, "POINT_001", "충전 금액이 0보다 커야 합니다."),

  MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "ORDER_002", "존재하지 않는 메뉴입니다."),
  POINT_NOT_ENOUGH(HttpStatus.BAD_REQUEST, "ORDER_001", "포인트가 부족합니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
