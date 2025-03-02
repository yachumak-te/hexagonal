package com.example.adapter.web.in.order;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.example.adapter.web.api_rest.model.CreateOrderRequestDTO;
import com.example.adapter.web.api_rest.model.OrderDTO;
import com.example.app.port.in.order.CreateOrderUseCase;
import com.example.domain.order.Order;



@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    CreateOrderUseCase.CreateOrderCommand toCreateOrderCommand(CreateOrderRequestDTO dto);

    OrderDTO toDto(Order order);
}
