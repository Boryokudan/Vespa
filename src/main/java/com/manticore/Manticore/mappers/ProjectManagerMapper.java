package com.manticore.Manticore.mappers;

import com.manticore.Manticore.user_dtos.ProjectManagerDto;
import com.manticore.Manticore.models.user_models.ProjectManager;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectManagerMapper {

    ProjectManagerDto toDto(ProjectManager projectManager);
    ProjectManager toEntity(ProjectManagerDto projectManagerDto);
    List<ProjectManagerDto> toDtoList(List<ProjectManager> projectManagers);
    List<ProjectManager> toEntityList(List<ProjectManagerDto> projectManagerDtos);
}
