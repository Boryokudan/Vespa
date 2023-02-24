package com.manticore.Manticore.controllers.rest_controllers;

import com.manticore.Manticore.services.DeveloperService;
import com.manticore.Manticore.dtos.user_dtos.DeveloperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/dev", produces = "application/json")
@RequiredArgsConstructor
public class DevelopersRestController {
    private final DeveloperService developerService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<DeveloperDto> getDevelopersList() {
        return developerService.getAllDeveloperDtos();
    }
}
