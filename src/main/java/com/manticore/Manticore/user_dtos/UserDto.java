package com.manticore.Manticore.user_dtos;

import com.manticore.Manticore.dtos.PermissionDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String fullName;
    private String email;
    private PermissionDto permission;
}