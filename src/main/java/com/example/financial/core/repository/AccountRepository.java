package com.example.financial.core.repository;

import com.example.financial.core.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, String> {

    boolean existsByOwnerId(UUID ownerId);
}
