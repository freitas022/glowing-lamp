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

    public Customer(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
}
