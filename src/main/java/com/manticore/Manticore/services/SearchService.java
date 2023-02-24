package com.manticore.Manticore.services;


import com.manticore.Manticore.dtos.ProjectDto;
import com.manticore.Manticore.dtos.user_dtos.UserDto;

import java.util.List;

public interface SearchService {

    List<ProjectDto> searchProjects(String query);

    List<ProjectDto> searchProjectsWithFilteredTickets(String query);

    ProjectDto searchProjectTickets(Long projectId, String query);
    List<UserDto> searchUsers(String query);
}
