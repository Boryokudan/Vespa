package com.manticore.Manticore.services;

import com.manticore.Manticore.dtos.ProjectDto;
import com.manticore.Manticore.models.Project;
import com.manticore.Manticore.models.user_models.Developer;
import com.manticore.Manticore.models.user_models.Submitter;

import java.util.List;

public interface ProjectService {
    Project getProjectById(Long id);
    List<Project> getAllProjectsForCurrentUser();
    List<Project> getAllProjectsByProjectManagerId(Long pmId);
    List<Project> getAllProjectsByDeveloper(Developer developer);
    List<Project> getAllProjectsBySubmitter(Submitter submitter);
    Project addProject(Project project);
    Project updateProject(Project project);
    void deleteProjectTicketById(Long id);
    void deleteProject(Long id);

    // <------------- Data Transfer Object Methods ------------->
    ProjectDto getProjectDtoById(Long id);
    List<ProjectDto> getAllProjectDtosForCurrentUser();
    List<ProjectDto> getAllProjectsDtosByProjectManagerId(Long pmId);
    ProjectDto addProjectDto(ProjectDto projectDto);
    ProjectDto updateProjectDto(ProjectDto projectDto);
}
