package com.fintech.jbanking.modules.account.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fintech.jbanking.modules.account.dto.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

        // Catch all exceptions which are not handled by other handlers
        @ExceptionHandler(value = Exception.class)
        ResponseEntity<ApiResponse> handleUncategorizedException(Exception e) {
                ErrorCode errorCode = ErrorCode.UNCATEGORIZED_EXCEPTION;

                return ResponseEntity.status(errorCode.getStatusCode())
                                .body(ApiResponse.builder()
                                                .message(errorCode.getMessage())
                                                .code(errorCode.getCode())
                                                .build());
        }

        // Catch AppException
        @ExceptionHandler(value = AppException.class)
        ResponseEntity<ApiResponse> handleAppException(AppException e) {
                ErrorCode errorCode = e.getErrorCode();

                return ResponseEntity.status(errorCode.getStatusCode())
                                .body(ApiResponse.builder()
                                                .message(errorCode.getMessage())
                                                .code(errorCode.getCode())
                                                .build());
        }

        // Catch IllegalArgumentException
        @ExceptionHandler(value = IllegalArgumentException.class)
        ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException e) {
                ErrorCode errorCode = ErrorCode.ILLEGAL_ARGUMENT;

                return ResponseEntity.status(errorCode.getStatusCode())
                                .body(ApiResponse.builder()
                                                .message(errorCode.getMessage())
                                                .code(errorCode.getCode())
                                                .build());
        }
}
