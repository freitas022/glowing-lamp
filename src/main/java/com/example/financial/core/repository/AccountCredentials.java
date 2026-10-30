package com.example.financial.core.repository;

import java.util.UUID;

public interface AccountCredentials {
    String getAccountNumber();
    String getPasswordHash();
    UUID getCustomerId();
    String getRole();
}
