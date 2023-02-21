package com.manticore.Manticore.services.implementations;

import com.manticore.Manticore.dtos.ProjectDto;
import com.manticore.Manticore.mappers.ProjectMapper;
import com.manticore.Manticore.models.Project;
import com.manticore.Manticore.models.Ticket;
import com.manticore.Manticore.repositories.*;
import com.manticore.Manticore.services.ProjectService;
import com.manticore.Manticore.services.UserService;
import com.manticore.Manticore.models.user_models.Developer;
import com.manticore.Manticore.models.user_models.Submitter;
import com.manticore.Manticore.models.user_models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImplementation implements ProjectService {
    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final PermissionRepository permissionRepository;
    private final DeveloperRepository developerRepository;
    private final SubmitterRepository submitterRepository;
    private final TicketRepository ticketRepository;


    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Project> getAllProjectsForCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        String currentUserPermissionLevel = currentUser.getPermission().getPermissionLevel();

        String adminRole = permissionRepository.findPermissionByPermissionLevel("ROLE_ADMIN").getPermissionLevel();
        String pmRole = permissionRepository.findPermissionByPermissionLevel("ROLE_PM").getPermissionLevel();
        String developerRole = permissionRepository.findPermissionByPermissionLevel("ROLE_DEV").getPermissionLevel();
        String submitterRole = permissionRepository.findPermissionByPermissionLevel("ROLE_SUB").getPermissionLevel();

        List<Project> projects = projectRepository.findAll(Sort.by("title"));
        if (currentUserPermissionLevel.equals(adminRole)) {
            return projects;
        }
        else if (currentUserPermissionLevel.equals(pmRole)) {
            List<Project> projectsOfPm = new ArrayList<>();
            projects.stream().forEach(project -> {
                if (project.getProjectManager() != null && project.getProjectManager().getEmail().equals(currentUser.getEmail())) {
                    projectsOfPm.add(project);
                }
            });
            return projectsOfPm;
        }
        else if (currentUserPermissionLevel.equals(developerRole)) {
            Developer developer = developerRepository.findDeveloperByEmail(currentUser.getEmail());
            List<Project> projectsOfDev = projects.stream().filter(project -> project.getTeam().contains(developer)).toList();
            List<Project> projectsOfDevWithFilteredTickets = new ArrayList<>();

            for (Project p : projectsOfDev) {
                List<Ticket> tickets = p.getTickets().stream().filter(
                        ticket ->
                                ticket.getAssignee() != null && ticket.getAssignee().getId().equals(developer.getId())
                ).toList();
                p.setTickets(tickets);
                projectsOfDevWithFilteredTickets.add(p);
            }
            return projectsOfDevWithFilteredTickets;
        }
        else if (currentUserPermissionLevel.equals(submitterRole)) {
            Submitter submitter = submitterRepository.findSubmitterByEmail(currentUser.getEmail());
            List<Project> projectsOfSub = projects.stream().filter(project -> project.getSubmitters().contains(submitter)).toList();
            List<Project> projectsOfSubWithFilteredTickets = new ArrayList<>();
            for (Project p : projectsOfSub) {
                List<Ticket> tickets = p.getTickets().stream().filter(
                        ticket ->
                                ticket.getOwner() != null && ticket.getOwner().getId().equals(submitter.getId())
                ).toList();
                p.setTickets(tickets);
                projectsOfSubWithFilteredTickets.add(p);
            }
            return projectsOfSubWithFilteredTickets;
        }
        return null;
    }

    @Override
    public List<Project> getAllProjectsByProjectManagerId(Long pmId) {
        return projectRepository.findAllByProjectManagerId(pmId);
    }

    @Override
    public List<Project> getAllProjectsByDeveloper(Developer developer) {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().filter(project -> project.getTeam().contains(developer)).collect(Collectors.toList());
    }

    @Override
    public List<Project> getAllProjectsBySubmitter(Submitter submitter) {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().filter(project -> project.getSubmitters().contains(submitter)).collect(Collectors.toList());
    }


    @Override
    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void deleteProjectTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        Project project = projectRepository.findById(ticket.getProjectId()).orElseThrow();
        project.getTickets().remove(ticket);
        projectRepository.save(project);
        ticketRepository.deleteById(id);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    // <------------- Data Transfer Object Methods ------------->
    @Override
    public ProjectDto getProjectDtoById(Long id) {
        return projectMapper.toDto(getProjectById(id));
    }

    @Override
    public List<ProjectDto> getAllProjectDtosForCurrentUser() {
        return projectMapper.toDtoList(getAllProjectsForCurrentUser());
    }

    @Override
    public List<ProjectDto> getAllProjectsDtosByProjectManagerId(Long pmId) {
        return null;
    }

    @Override
    public ProjectDto addProjectDto(ProjectDto projectDto) {
        return projectMapper.toDto(addProject(projectMapper.toEntity(projectDto)));
    }

    @Override
    public ProjectDto updateProjectDto(ProjectDto projectDto) {
        return projectMapper.toDto(updateProject(projectMapper.toEntity(projectDto)));
    }
}
