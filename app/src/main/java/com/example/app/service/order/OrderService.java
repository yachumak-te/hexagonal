package com.example.app.service.order;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.app.port.in.order.CreateOrderUseCase;
import com.example.app.port.in.order.GetOrderDetailsUseCase;
import com.example.app.port.out.order.OrderPort;
import com.example.app.port.out.user.UserPort;
import com.example.domain.order.Order;
import com.example.domain.order.OrderDetails;
import com.example.domain.user.User;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class OrderService implements CreateOrderUseCase, GetOrderDetailsUseCase {

    private final OrderPort orderPort;

    private final UserPort userPort;

    @Override
    public Order createOrder(CreateOrderCommand command) {
        final var order = Order.builder()
                               .placedAt(OffsetDateTime.now())
                               .userId(command.userId())
                               .id(UUID.randomUUID()).build();
        return orderPort.save(order);
    }

    @Override
    public List<OrderDetails> getOrderByQuery(GetOrderDetailsQuery query) {
        final var orders = orderPort.findByPlacedDateRange(query.placedFrom(), query.placedTo());
        return orders.stream().map(this::buildOrderDetails).toList();
    }

    private OrderDetails buildOrderDetails(Order order) {
        final var userName = userPort.findUseById(order.userId())
                .map(User::name).orElse("UNKNOWN_USER");

        return OrderDetails.builder()
                .userName(userName)
                .placedAt(order.placedAt()).build();
    }
}
