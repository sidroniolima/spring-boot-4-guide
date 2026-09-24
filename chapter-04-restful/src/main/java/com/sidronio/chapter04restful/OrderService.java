package com.sidronio.chapter04restful;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderDAO;

    public OrderService(OrderRepository orderDAO) {
        this.orderDAO = orderDAO;
    }

    public List<OrderSummary> findAll() {
        return orderDAO.listAll();
    }

    public Optional<OrderSummary> getOrder(UUID uuid) {
        return orderDAO.findById(uuid);
    }
}
