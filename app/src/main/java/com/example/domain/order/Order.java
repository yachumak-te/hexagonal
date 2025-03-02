package com.example.domain.order;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.Builder;


@Builder
public record Order(UUID id, OffsetDateTime placedAt, UUID userId) {
}
