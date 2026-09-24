package com.sidronio.chapter04restful;

import java.util.UUID;

public record OrderSummary(UUID id, String number, String customerName, float amount) {
}
