/*
(Mục đích: Dữ liệu trả về cho Client)
*/
package com.fintech.jbanking.modules.account.dto.response;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.Builder;

@Builder
public record AccountResponse(
        Long id,
        String accountNumber,
        String ownerName,
        BigDecimal balance,
        String status,
        Instant createdAt) {
}
