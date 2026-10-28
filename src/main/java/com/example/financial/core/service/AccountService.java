package com.example.financial.core.service;

import com.example.financial.core.domain.Account;
import com.example.financial.core.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountNumberGeneratorService numberGenerator;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createForCustomer(UUID ownerId) {
        if (accountRepository.existsByOwnerId(ownerId)) {
            return;
        }

        Account account = new Account(
                numberGenerator.generate(),
                ownerId
        );

        accountRepository.save(account);
        log.info("Conta gerada: {} para o cliente: {}", account.getNumber(), ownerId);
    }
}
