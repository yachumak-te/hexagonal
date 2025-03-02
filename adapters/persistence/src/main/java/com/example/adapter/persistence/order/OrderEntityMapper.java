package com.example.adapter.persistence.order;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.example.domain.order.Order;



@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderEntityMapper {
    OrderEntity fromModel(Order order);

    Order toModel(OrderEntity entity);
}
