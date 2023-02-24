package com.manticore.Manticore.mappers;

import com.manticore.Manticore.dtos.user_dtos.DeveloperDto;
import com.manticore.Manticore.models.user_models.Developer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeveloperMapper {

    DeveloperDto toDto(Developer developer);
    Developer toEntity(DeveloperDto developerDto);
    List<DeveloperDto> toDtoList(List<Developer> developers);
    List<Developer> toEntityList(List<DeveloperDto> developerDtos);
}
