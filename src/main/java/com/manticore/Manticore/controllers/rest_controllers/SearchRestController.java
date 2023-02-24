package com.manticore.Manticore.controllers.rest_controllers;

import com.manticore.Manticore.dtos.ProjectDto;
import com.manticore.Manticore.dtos.user_dtos.UserDto;
import com.manticore.Manticore.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/search", produces = "application/json")
@RequiredArgsConstructor
public class SearchRestController {

    private final SearchService searchService;

    @GetMapping("/projects")
    public List<ProjectDto> searchProjects(@RequestParam(name = "query") String query) {
        return searchService.searchProjects(query);
    }

    @GetMapping(value = "/projects", params = "filtered_tickets=true")
    public List<ProjectDto> searchProjectsWithFilteredTickets(@RequestParam(name = "query") String query) {
        return searchService.searchProjectsWithFilteredTickets(query);
    }

    @GetMapping("/tickets")
    public ProjectDto searchProjectTickets(@RequestParam(name = "p_id") Long projectId,
                                           @RequestParam(name = "query") String query) {
        return searchService.searchProjectTickets(projectId, query);
    }

    @GetMapping("/users")
    public List<UserDto> searchUsers(@RequestParam(name = "query") String query) {
        return searchService.searchUsers(query);
    }
}
