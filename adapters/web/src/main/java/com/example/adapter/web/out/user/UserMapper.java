package com.example.adapter.web.out.user;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.example.adapter.web.api_rest_clients.users.model.UserDTO;
import com.example.domain.user.User;



@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User fromDTO(UserDTO dto);
}
