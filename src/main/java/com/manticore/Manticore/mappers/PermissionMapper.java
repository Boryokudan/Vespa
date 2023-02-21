package com.manticore.Manticore.mappers;

import com.manticore.Manticore.dtos.PermissionDto;
import com.manticore.Manticore.models.Permission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionDto toDto(Permission permission);
    Permission toEntity(PermissionDto permissionDto);
    List<PermissionDto> toDtoList(List<Permission> permissions);
    List<Permission> toEntityList(List<PermissionDto> permissionDtos);
}
