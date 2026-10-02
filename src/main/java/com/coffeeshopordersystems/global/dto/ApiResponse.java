package com.coffeeshopordersystems.global.dto;


import com.coffeeshopordersystems.global.exception.ErrorCode;

public record ApiResponse<T>(String code, String message, T data){

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>("SUCCESS", message, data);
    }

    public static ApiResponse<Void> fail(ErrorCode errorCode){
        return new ApiResponse<>(errorCode.getCode(), errorCode.getMessage(), null);
    }
}
