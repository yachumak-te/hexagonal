package com.example.adapter.web.in.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.adapter.web.api_rest.api.OrdersApi;
import com.example.adapter.web.api_rest.model.CreateOrderRequestDTO;
import com.example.adapter.web.api_rest.model.OrderDTO;
import com.example.app.port.in.order.CreateOrderUseCase;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class OrderController implements OrdersApi {

    private final OrderMapper orderMapper;

    private final CreateOrderUseCase createOrderUseCase;

    @Override
    public ResponseEntity<OrderDTO> createOrder(CreateOrderRequestDTO dto) {
        final var command = orderMapper.toCreateOrderCommand(dto);
        final var order = createOrderUseCase.createOrder(command);
        return ResponseEntity.ok(orderMapper.toDto(order));
    }
}
