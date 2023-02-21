package com.manticore.Manticore.controllers.rest_controllers;

import com.manticore.Manticore.dtos.ProjectDto;
import com.manticore.Manticore.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/projects", produces = "application/json")
@RequiredArgsConstructor
public class ProjectRestController {

    private final ProjectService projectService;

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ProjectDto getProject(@PathVariable(name = "id") Long id) {
        return projectService.getProjectDtoById(id);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProjectDtosForCurrentUser();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProjectDto createNewProject(@RequestBody ProjectDto newProjectDto) {
        return projectService.addProjectDto(newProjectDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProjectDto updateProject(@RequestBody ProjectDto projectDto) {
        return projectService.updateProjectDto(projectDto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProject(@PathVariable(name = "id") Long id) {
        projectService.deleteProject(id);
    }
}
