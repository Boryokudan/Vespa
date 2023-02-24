package com.manticore.Manticore.mappers;

import com.manticore.Manticore.dtos.PermissionDto;
import com.manticore.Manticore.models.Permission;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-24T12:56:05+0600",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.4.1 (BellSoft)"
)
@Component
public class PermissionMapperImpl implements PermissionMapper {

    @Override
    public PermissionDto toDto(Permission permission) {
        if ( permission == null ) {
            return null;
        }

        PermissionDto permissionDto = new PermissionDto();

        permissionDto.setId( permission.getId() );
        permissionDto.setPermissionLevel( permission.getPermissionLevel() );

        return permissionDto;
    }

    @Override
    public Permission toEntity(PermissionDto permissionDto) {
        if ( permissionDto == null ) {
            return null;
        }

        Permission permission = new Permission();

        permission.setId( permissionDto.getId() );
        permission.setPermissionLevel( permissionDto.getPermissionLevel() );

        return permission;
    }

    @Override
    public List<PermissionDto> toDtoList(List<Permission> permissions) {
        if ( permissions == null ) {
            return null;
        }

        List<PermissionDto> list = new ArrayList<PermissionDto>( permissions.size() );
        for ( Permission permission : permissions ) {
            list.add( toDto( permission ) );
        }

        return list;
    }

    @Override
    public List<Permission> toEntityList(List<PermissionDto> permissionDtos) {
        if ( permissionDtos == null ) {
            return null;
        }

        List<Permission> list = new ArrayList<Permission>( permissionDtos.size() );
        for ( PermissionDto permissionDto : permissionDtos ) {
            list.add( toEntity( permissionDto ) );
        }

        return list;
    }
}
