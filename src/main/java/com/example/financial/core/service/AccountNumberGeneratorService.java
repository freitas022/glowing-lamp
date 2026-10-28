package com.example.financial.core.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AccountNumberGeneratorService {

    private static final int ACCOUNT_LENGTH = 8;
    private static final SecureRandom RANDOM = new SecureRandom();

    public String generate() {
        String baseNumber = generateBaseNumber();
        int checkDigit = calculateCheckDigit(baseNumber);

        return baseNumber + "-" + checkDigit;

    }

    private String generateBaseNumber() {
        StringBuilder number = new StringBuilder(ACCOUNT_LENGTH);

        for (int i = 0; i < ACCOUNT_LENGTH; i++) {
            number.append(RANDOM.nextInt(10));
        }

        return number.toString();
    }

    private int calculateCheckDigit(String number) {
        int sum = 0;
        int weight = 2;

        for (int i = number.length() - 1; i >= 0; i--) {
            sum += Character.getNumericValue(number.charAt(i)) * weight;

            weight = weight == 9 ? 2 : weight + 1;
        }

        int remainder = sum % 11;
        int digit = 11 - remainder;

        return digit >= 10 ? 0 : digit;
    }
}
