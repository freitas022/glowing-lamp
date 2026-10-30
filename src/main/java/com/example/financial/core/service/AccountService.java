package com.example.financial.core.service;

import com.example.financial.core.domain.Account;
import com.example.financial.core.repository.AccountCredentials;
import com.example.financial.core.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    public AccountCredentials getCredentialsByNumber(String number) {
        return accountRepository.findCredentialsByNumber(number)
                .orElseThrow(() ->
                        new BadCredentialsException("Credenciais inválidas."));
    }

    public Account getAccount(String number, UUID ownerId) {
        return accountRepository.findByNumberAndOwnerId(number, ownerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
