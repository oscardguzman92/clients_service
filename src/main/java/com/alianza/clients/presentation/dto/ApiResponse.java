package com.alianza.clients.presentation.dto;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public ApiResponse(boolean success, T message, T data) {
        this.success = success;
        this.message = (String) message;
        this.data = data;
    }


}
