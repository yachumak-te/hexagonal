package com.example.app.port.in.order;

import java.util.UUID;

import com.example.domain.order.Order;

import lombok.Builder;

public interface CreateOrderUseCase {
    Order createOrder(CreateOrderCommand command);

    @Builder
    record CreateOrderCommand(UUID userId) {

    }
}
