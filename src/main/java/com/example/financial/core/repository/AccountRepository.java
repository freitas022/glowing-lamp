package com.example.financial.core.repository;

import com.example.financial.core.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, String> {

    boolean existsByOwnerId(UUID ownerId);

    @Query("""
                select a.number as accountNumber,
                       c.passwordHash as passwordHash,
                       c.id as customerId,
                       c.role as role
                from Account a
                join Customer c on c.id = a.ownerId
                where a.number = :accountNumber
            """)
    Optional<AccountCredentials> findCredentialsByNumber(String accountNumber);

    Optional<Account> findByNumberAndOwnerId(String number, UUID ownerId);


}
