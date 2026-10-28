package com.example.financial.core.listener;

import com.example.financial.core.event.CustomerRegisteredEvent;
import com.example.financial.core.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerListener {

    private final AccountService accountService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(CustomerRegisteredEvent event) {
        log.info("Evento recebido: {}", event.customerId());
        accountService.createForCustomer(event.customerId());
    }
}
