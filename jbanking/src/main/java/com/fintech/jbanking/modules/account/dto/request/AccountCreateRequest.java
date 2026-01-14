/*
(Mục đích: Dữ liệu đầu vào khi tạo tài khoản)
*/
package com.fintech.jbanking.modules.account.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccountCreateRequest(
                @NotBlank(message = "Owner name is required") String ownerName,

                @NotNull(message = "Initial balance is required") @Min(value = 0, message = "Initial balance must be greater than or equal to 0") BigDecimal initialBalance) {
}