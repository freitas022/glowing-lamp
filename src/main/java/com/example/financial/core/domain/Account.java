package com.example.financial.core.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_accounts")
@NoArgsConstructor
@Getter
public class Account {

    @Id
    @Column(length = 10, nullable = false, unique = true)
    private String number;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance;

    @Column(name = "owner_id", nullable = false, unique = true)
    private UUID ownerId;

    public Account(String number, UUID ownerId) {
        this.balance = BigDecimal.ZERO;
        this.number = number;
        this.ownerId = Objects.requireNonNull(ownerId);
    }
}
