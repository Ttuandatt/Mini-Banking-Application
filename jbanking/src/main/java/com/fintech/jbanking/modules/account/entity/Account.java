/*
(Mục đích: Ánh xạ bảng accounts trong DB)
*/
package com.fintech.jbanking.modules.account.entity;

import com.fintech.jbanking.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account extends BaseEntity {// <--- Kế thừa BaseEntity để có ID
    @Column(unique = true, nullable = false, length = 20)
    String accountNumber;

    @Column(nullable = false)
    String ownerName;

    @Column(nullable = false, precision = 19, scale = 2)
    BigDecimal balance;

    @Builder.Default
    String currency = "VND";

    @Builder.Default
    String status = "ACTIVE";

    @Version // Optimistic Locking
    Long version;

}
