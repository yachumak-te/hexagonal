package com.example.domain.order;

import java.time.OffsetDateTime;

import lombok.Builder;



@Builder
public record OrderDetails(OffsetDateTime placedAt, String userName) {
}
