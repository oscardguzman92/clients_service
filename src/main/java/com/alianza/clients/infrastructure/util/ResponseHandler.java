package com.alianza.clients.infrastructure.util;

import com.alianza.clients.presentation.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<ApiResponse<String>> handleException(Exception e, String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, message, e.getMessage()));
    }

    public static ResponseEntity<ApiResponse<String>> handleExceptionNotFound(Exception e, String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, message, e.getMessage()));
    }

    public static ResponseEntity<?> handleResponse(ResponseEntity<?> response) {
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("response: ");
            System.out.println(Collections.singletonList(response));
            return ResponseEntity.ok(new ApiResponse<>(true, "Success", Collections.singletonList(response.getBody())));
        }
        return ResponseEntity.status(response.getStatusCode()).body(new ApiResponse<>(false, "API error", null));
    }

}
