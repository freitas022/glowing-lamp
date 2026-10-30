package com.example.financial.core.dto;

import java.math.BigDecimal;

public record AccountResponse(String number, BigDecimal balance) {
}
