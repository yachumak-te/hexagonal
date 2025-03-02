package com.example.adapter.web.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import com.example.adapter.web.out.user.UserAdapter;
import com.example.adapter.web.out.user.UserMapperImpl;
import com.example.adapter.web.out.user.UserRestClientConfiguration;
import com.example.domain.user.User;



@ContextConfiguration(classes = { UserAdapter.class, UserRestClientConfiguration.class })
@Import(value = { UserMapperImpl.class })
@EnableWireMock({ @ConfigureWireMock(filesUnderClasspath = "mocks/users-api", port = 8080) })
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "rest.client.users.base-url=http://localhost:8080")
public class UserAdapterIT {

    @Autowired
    private UserAdapter adapter;

    @Test
    void testFindUseById() {
        // given
        final var userId = UUID.fromString("3cd63e13-8747-472c-bc1e-3739cad416f4");

        // when
        final var actual = adapter.findUseById(userId);

        // then
        final var expected = User.builder()
                                 .name("John Connor")
                                 .email("john.connor@yahoo.com")
                                 .createdAt(OffsetDateTime.parse("2025-01-01T00:00Z"))
                                 .id(userId).build();
        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }
}
