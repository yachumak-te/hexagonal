package com.example.adapter.web.out.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.adapter.web.api_rest_clients.users.api.UsersApi;
import com.example.adapter.web.api_rest_clients.users.invoker.ApiClient;



@Configuration
public class UserRestClientConfiguration {

    @Bean("usersApiClient")
    public ApiClient usersApiClient(@Value("${rest.client.users.base-url}") String baseUrl) {
        return new ApiClient().setBasePath(baseUrl);
    }

    @Bean
    public UsersApi usersApi(final @Qualifier("usersApiClient") ApiClient usersApiClient) {
        return new UsersApi(usersApiClient);
    }
}
