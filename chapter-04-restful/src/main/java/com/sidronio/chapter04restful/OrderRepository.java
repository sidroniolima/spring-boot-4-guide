package com.sidronio.chapter04restful;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OrderRepository {
    private final List<OrderSummary> orders = List.of(
            new OrderSummary(UUID.randomUUID(),
                        "00001",
                                "Sidronio",
                                1015.25f),
                                new OrderSummary(UUID.randomUUID(),
                        "00002",
                                "Lima",
                                744.13f));

    public List<OrderSummary> listAll() {
        return orders;
    }

    public Optional<OrderSummary> findById(UUID uuid) {
        return orders.stream()
                .filter(order -> order.id().equals(uuid))
                .findFirst();
    }
}
