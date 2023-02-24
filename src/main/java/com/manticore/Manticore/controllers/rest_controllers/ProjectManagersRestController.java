package com.manticore.Manticore.controllers.rest_controllers;

import com.manticore.Manticore.services.ProjectManagerService;
import com.manticore.Manticore.dtos.user_dtos.ProjectManagerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/pm", produces = "application/json")
@RequiredArgsConstructor
public class ProjectManagersRestController {
    private final ProjectManagerService projectManagerService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<ProjectManagerDto> getProjectManagersList() {
        return projectManagerService.getAllProjectManagerDtos();
    }
}
