package com.example.financial.core.service;

import com.example.financial.core.domain.Customer;
import com.example.financial.core.dto.CreateCustomerRequest;
import com.example.financial.core.event.CustomerRegisteredEvent;
import com.example.financial.core.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void create(CreateCustomerRequest request) {
        LocalDate date = LocalDate.parse(request.birthDate());
        Customer customer = customerRepository.save(
                new Customer(request.name(), date, passwordEncoder.encode(request.password()))
        );

        eventPublisher.publishEvent(
                new CustomerRegisteredEvent(customer.getId())
        );
    }
}
