/*
(Mục đích: Giao tiếp với Database)
*/
package com.fintech.jbanking.modules.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.jbanking.modules.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByAccountNumber(String accountNumber);
}
