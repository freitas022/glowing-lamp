package com.example.financial.core.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_customers")
@NoArgsConstructor
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "birth_date")
    private LocalDate birthDate;

    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "role")
    private ERole role;

    public Customer(String name, LocalDate birthDate, String passwordHash) {
        this.name = name;
        this.birthDate = birthDate;
        this.passwordHash = passwordHash;
        this.role = ERole.CUSTOMER;
    }
}
