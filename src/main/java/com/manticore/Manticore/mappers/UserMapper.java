package com.manticore.Manticore.mappers;

import com.manticore.Manticore.dtos.user_dtos.UserDto;
import com.manticore.Manticore.models.user_models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);
    User toEntity(UserDto userDto);
    List<UserDto> toDtoList(List<User> users);
    List<User> toEntityList(List<UserDto> userDtos);
}
