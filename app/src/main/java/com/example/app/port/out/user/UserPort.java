package com.example.app.port.out.user;

import java.util.Optional;
import java.util.UUID;

import com.example.domain.user.User;



public interface UserPort {
    Optional<User> findUseById(UUID id);
}
