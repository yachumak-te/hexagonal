package com.example.adapter.persistence.out.order;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;



@Component
public interface OrderJpaAdapter extends JpaRepository<OrderEntity, UUID> {
}
