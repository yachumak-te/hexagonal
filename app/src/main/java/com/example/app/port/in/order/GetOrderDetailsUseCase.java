package com.example.app.port.in.order;

import java.time.OffsetDateTime;
import java.util.List;

import com.example.domain.order.Order;
import com.example.domain.order.OrderDetails;

import lombok.Builder;



public interface GetOrderDetailsUseCase {
    List<OrderDetails> getOrderByQuery(GetOrderDetailsQuery query);

    @Builder
    record GetOrderDetailsQuery(OffsetDateTime placedFrom, OffsetDateTime placedTo) {
    }
}
