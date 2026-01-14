package com.fintech.jbanking.modules.account.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.jbanking.modules.account.dto.request.AccountCreateRequest;
import com.fintech.jbanking.modules.account.dto.response.AccountBalanceResponse;
import com.fintech.jbanking.modules.account.dto.response.AccountResponse;
import com.fintech.jbanking.modules.account.dto.response.ApiResponse;
import com.fintech.jbanking.modules.account.service.AccountService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    AccountService accountService;

    // Create Account
    @PostMapping("/create")
    public ApiResponse<AccountResponse> createAccount(@Valid @RequestBody AccountCreateRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .message("Account created successfully")
                .result(accountService.createAccount(request))
                .build();
    }

    // Get All Accounts
    @GetMapping
    public ApiResponse<List<AccountResponse>> getAllAccounts() {
        return ApiResponse.<List<AccountResponse>>builder()
                .message("List of accounts")
                .result(accountService.getAllAccounts())
                .build();
    }

    // Get Account by Account Number
    @GetMapping("/{accountNumber}")
    public ApiResponse<AccountResponse> getAccountByAccountNumber(@PathVariable String accountNumber) {
        return ApiResponse.<AccountResponse>builder()
                .message("Account found")
                .result(accountService.getAccountByAccountNumber(accountNumber))
                .build();
    }

    // Get Account Balance
    @GetMapping("/{accountNumber}/balance")
    public ApiResponse<AccountBalanceResponse> getAccountBalance(@PathVariable String accountNumber) {
        return ApiResponse.<AccountBalanceResponse>builder()
                .message("Current account balance")
                .result(accountService.getAccountBalance(accountNumber))
                .build();
    }

}
