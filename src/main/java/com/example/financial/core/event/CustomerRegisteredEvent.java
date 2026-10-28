package com.example.financial.core.event;

import java.util.UUID;

public record CustomerRegisteredEvent(UUID customerId) {
}
