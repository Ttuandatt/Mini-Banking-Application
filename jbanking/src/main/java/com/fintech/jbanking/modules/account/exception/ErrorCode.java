package com.fintech.jbanking.modules.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    ACCOUNT_NOT_FOUND(1001, "Account not found", HttpStatus.NOT_FOUND),
    ACCOUNT_EXISTS(1002, "Account number already exists", HttpStatus.BAD_REQUEST),
    INSUFFICIENT_BALANCE(1003, "Insufficient balance", HttpStatus.BAD_REQUEST),
    INVALID_AMOUNT(1004, "Amount must be greater than zero", HttpStatus.BAD_REQUEST),
    ACCOUNT_LOCKED(1005, "Account is locked", HttpStatus.FORBIDDEN),
    ACCOUNT_CLOSED(1006, "Account is closed", HttpStatus.FORBIDDEN),
    SAME_ACCOUNT_TRANSFER(1007, "Cannot transfer to the same account", HttpStatus.BAD_REQUEST),
    ILLEGAL_ARGUMENT(1008, "Illegal argument", HttpStatus.BAD_REQUEST),
    ;

    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
