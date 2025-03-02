package com.example.adapter.web.out.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.example.adapter.web.api_rest_clients.users.api.UsersApi;
import com.example.app.port.out.user.UserPort;
import com.example.domain.user.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Component
@RequiredArgsConstructor
@Slf4j
public class UserAdapter implements UserPort {

    private final UsersApi usersApi;

    private final UserMapper userMapper;

    @Override
    public Optional<User> findUseById(UUID id) {
        try {
            final var dto = usersApi.getUserById(id);
            return Optional.ofNullable(userMapper.fromDTO(dto));
        } catch (HttpClientErrorException.NotFound e) {
            log.warn("Can't find user with id {}", id);
            return Optional.empty();
        }
    }
}
