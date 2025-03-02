package com.example.adapter.persistence.order;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;



@Component
public interface OrderJpaAdapter extends JpaRepository<OrderEntity, UUID> {
}
