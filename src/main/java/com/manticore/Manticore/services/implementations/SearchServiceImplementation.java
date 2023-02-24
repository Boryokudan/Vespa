package com.manticore.Manticore.services.implementations;

import com.manticore.Manticore.dtos.ProjectDto;
import com.manticore.Manticore.dtos.user_dtos.UserDto;
import com.manticore.Manticore.mappers.ProjectMapper;
import com.manticore.Manticore.mappers.UserMapper;
import com.manticore.Manticore.models.Project;
import com.manticore.Manticore.models.Ticket;
import com.manticore.Manticore.repositories.ProjectRepository;
import com.manticore.Manticore.repositories.UserRepository;
import com.manticore.Manticore.services.ProjectService;
import com.manticore.Manticore.services.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImplementation implements SearchService {

    private final ProjectService projectService;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;
    private final UserMapper userMapper;

    @Override
    public List<ProjectDto> searchProjects(String query) {
        return projectMapper.toDtoList(projectRepository.processSearchQuery(query.toLowerCase(), Sort.by(Sort.Direction.ASC, "title")));
    }

    @Override
    public List<ProjectDto> searchProjectsWithFilteredTickets(String query) {
        List<Project> projects = projectService.getAllProjectsForCurrentUser();
        List<Project> searchResults = projects.stream().filter(
                project -> project.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                           project.getStatus().toLowerCase().contains(query.toLowerCase())
                )
                .toList();

        return projectMapper.toDtoList(searchResults);
    }

    @Override
    public ProjectDto searchProjectTickets(Long projectId, String query) {
        String lowerCaseQuery = query.toLowerCase();
        Project project = projectRepository.findById(projectId).orElseThrow();
        List<Ticket> projectTickets = project.getTickets().stream().filter(
                ticket -> ticket.getTitle().toLowerCase().contains(lowerCaseQuery) ||
                          ticket.getType().toLowerCase().contains(lowerCaseQuery) ||
                          ticket.getPriority().toLowerCase().contains(lowerCaseQuery) ||
                          ticket.getStatus().toLowerCase().contains(lowerCaseQuery) ||
                          (ticket.getAssignee() != null && ticket.getAssignee().getFullName().toLowerCase().contains(lowerCaseQuery)) ||
                          (ticket.getOwner() != null && ticket.getOwner().getFullName().toLowerCase().contains(lowerCaseQuery))
                ).toList();
        project.setTickets(projectTickets);
        return projectMapper.toDto(project);
    }

    @Override
    public List<UserDto> searchUsers(String query) {
        return userMapper.toDtoList(userRepository.processSearchQuery(query.toLowerCase(), Sort.by(Sort.Direction.ASC, "fullName")));
    }
}
