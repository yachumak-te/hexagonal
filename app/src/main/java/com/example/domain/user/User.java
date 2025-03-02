package com.example.domain.user;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.Builder;


@Builder
public record User(UUID id, String name, OffsetDateTime createdAt) {
}
