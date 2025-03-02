package com.example.app.port.out.order;

import java.time.OffsetDateTime;
import java.util.List;

import com.example.domain.order.Order;



public interface OrderPort {

    Order save(Order order);

    List<Order> findByPlacedDateRange(OffsetDateTime placedFro, OffsetDateTime placedTo);
}
