package com.manticore.Manticore.mappers;

import com.manticore.Manticore.dtos.ProjectDto;
import com.manticore.Manticore.models.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDto toDto(Project project);
    Project toEntity(ProjectDto projectDto);
    List<ProjectDto> toDtoList(List<Project> projects);
    List<Project> toEntityList(List<ProjectDto> projectDtos);
}
