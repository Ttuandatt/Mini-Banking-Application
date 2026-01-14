package com.fintech.jbanking.modules.account.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fintech.jbanking.modules.account.dto.request.AccountCreateRequest;
import com.fintech.jbanking.modules.account.dto.response.AccountBalanceResponse;
import com.fintech.jbanking.modules.account.dto.response.AccountResponse;
import com.fintech.jbanking.modules.account.entity.Account;
import com.fintech.jbanking.modules.account.repository.AccountRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
    AccountRepository accountRepository;

    @Transactional
    public AccountResponse createAccount(AccountCreateRequest request) {
        // Logic sinh số tài khoản ngẫu nhiên
        String accountNumber = generateUniqueAccountNumber();

        // Tạo Entity từ Request
        Account newAccount = Account.builder()
                .accountNumber(accountNumber)
                .ownerName(request.ownerName())
                .balance(request.initialBalance())
                .status("ACTIVE")
                .currency("VND")
                .build();

        // Lưu vào DB
        Account savedAccount = accountRepository.save(newAccount);

        // Chuyển đổi sang Response
        return mapToAccountResponse(savedAccount);
    }

    private String generateUniqueAccountNumber() {
        String accNum;
        do {
            accNum = String.valueOf(Math.abs(UUID.randomUUID().hashCode())).substring(0, 10);
        } while (accountRepository.existsByAccountNumber(accNum));
        return accNum;
    }

    // Get all accounts
    public List<AccountResponse> getAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(this::mapToAccountResponse)
                .toList();
    }

    private AccountResponse mapToAccountResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .ownerName(account.getOwnerName())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .status(account.getStatus())
                .createdAt(account.getCreatedAt())
                .build();
    }

    // Get account by account number
    public AccountResponse getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber)
                .map(this::mapToAccountResponse)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public AccountBalanceResponse getAccountBalance(String accountNumber) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return new AccountBalanceResponse(account.getBalance(), account.getCurrency());
    }
}
