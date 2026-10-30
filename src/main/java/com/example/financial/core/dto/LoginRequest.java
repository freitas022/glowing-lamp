package com.example.financial.core.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank
        String number,
        @NotBlank @Size(min = 4, max = 4) @Pattern(regexp = "\\d{4}")
        String password
) {
}
