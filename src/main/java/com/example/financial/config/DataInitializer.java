package com.example.financial.config;

import com.example.financial.core.dto.CreateCustomerRequest;
import com.example.financial.core.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final CustomerService customerService;

    private static final String PASSWORD = "1234";

    @Override
    public void run(ApplicationArguments args) {
        customerService.create(new CreateCustomerRequest("Luciana", "2000-01-09", PASSWORD));
        customerService.create(new CreateCustomerRequest("Matheus", "2000-10-11", PASSWORD));
        customerService.create(new CreateCustomerRequest("Carlos", "2000-03-18", PASSWORD));

        log.info("Seed finalizado.");
    }
}
