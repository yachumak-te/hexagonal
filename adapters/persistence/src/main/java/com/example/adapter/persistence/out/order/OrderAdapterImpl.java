package com.example.adapter.persistence.out.order;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.app.port.out.order.OrderPort;
import com.example.domain.order.Order;

import lombok.RequiredArgsConstructor;



@Repository
@RequiredArgsConstructor
public class OrderAdapterImpl implements OrderPort {

    private final OrderEntityMapper mapper;

    private final OrderJpaAdapter adapter;

    @Override
    public Order save(Order order) {
        final var entity = mapper.fromModel(order);
        final var savedEntity = adapter.save(entity);
        return mapper.toModel(savedEntity);
    }

    @Override
    public List<Order> findByPlacedDateRange(OffsetDateTime placedFro, OffsetDateTime placedTo) {
        return List.of();
    }
}
