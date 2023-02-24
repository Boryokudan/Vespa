package com.manticore.Manticore.services.implementations;

import com.manticore.Manticore.mappers.UserMapper;
import com.manticore.Manticore.models.Project;
import com.manticore.Manticore.models.Ticket;
import com.manticore.Manticore.services.*;
import com.manticore.Manticore.dtos.user_dtos.UserDto;
import com.manticore.Manticore.models.user_models.Developer;
import com.manticore.Manticore.models.user_models.ProjectManager;
import com.manticore.Manticore.models.user_models.Submitter;
import com.manticore.Manticore.models.user_models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPermissionManagementServiceImplementation implements UserPermissionManagementService {
    private final UserMapper userMapper;
    private final PermissionService permissionService;
    private final UserService userService;
    private final ProjectService projectService;
    private final ProjectManagerService projectManagerService;
    private final DeveloperService developerService;
    private final SubmitterService submitterService;
    private final TicketService ticketService;

    @Override
    public List<UserDto> updateUserDtosRoles(List<UserDto> userDtos) {
        final String roleAdmin = "ROLE_ADMIN";
        final String rolePM = "ROLE_PM";
        final String roleDev = "ROLE_DEV";
        final String roleSub = "ROLE_SUB";

        List<User> users = userMapper.toEntityList(userDtos);

        for (User u : users) {
            String grantedPermissionLevel = permissionService.getPermissionById(u.getPermission().getId()).getPermissionLevel();
            User user = userService.getUserById(u.getId());
            String userPermissionLevel = user.getPermission().getPermissionLevel();

            switch (grantedPermissionLevel) {
                case (roleAdmin):
                    if (!userPermissionLevel.equals(roleAdmin)) grantAdminPermissionToExistingUser(user);
                    break;
                case (rolePM):
                    if (!userPermissionLevel.equals(rolePM)) grantProjectManagerPermissionToExistingUser(user);
                    break;
                case (roleDev):
                    if (!userPermissionLevel.equals(roleDev)) grantDeveloperPermissionToExistingUser(user);
                    break;
                case (roleSub):
                    if (!userPermissionLevel.equals(roleSub)) grantSubmitterPermissionToExistingUser(user);
                    break;
            }
        }
        return userService.getAllUserDtosSortedByFullName();
    }

    @Override
    public User grantAdminPermissionToExistingUser(User existingUser) {
        String currentPermissionLevel = existingUser.getPermission().getPermissionLevel();
        if (currentPermissionLevel.equals("ROLE_ADMIN")) return existingUser;

        switch (currentPermissionLevel) {
            case ("ROLE_PM"):
                ProjectManager pm = projectManagerService.getProjectManagerByEmail(existingUser.getEmail());
                List<Project> pmProjects = projectService.getAllProjectsByProjectManagerId(pm.getId());
                for (Project p : pmProjects) {
                    p.setProjectManager(null);
                    projectService.updateProject(p);
                }
                projectManagerService.deleteProjectManager(pm);
                break;
            case ("ROLE_DEV"):
                Developer developer = developerService.getDeveloperByEmail(existingUser.getEmail());
                List<Project> devProjects = projectService.getAllProjectsByDeveloper(developer);
                for (Project p : devProjects) {
                    p.getTeam().remove(developer);
                    projectService.updateProject(p);
                }
                List<Ticket> ticketsD = ticketService.getAllTickets();
                for (Ticket t : ticketsD) {
                    if (t.getAssignee() != null) {
                        if (t.getAssignee().getEmail().equals(developer.getEmail())) {
                            t.setAssignee(null);
                            ticketService.updateTicket(t);
                        }
                    }
                }
                developerService.deleteDeveloper(developer);
                break;
            case ("ROLE_SUB"):
                Submitter submitter = submitterService.getSubmitterByEmail(existingUser.getEmail());
                List<Project> subProjects = projectService.getAllProjectsBySubmitter(submitter);
                for (Project p : subProjects) {
                    p.
                            getSubmitters().remove(submitter);
                    projectService.updateProject(p);
                }
                List<Ticket> ticketsS = ticketService.getAllTickets();
                for (Ticket t : ticketsS) {
                    if (t.getOwner() != null) {
                        if (t.getOwner().getEmail().equals(submitter.getEmail())) {
                            t.setOwner(null);
                            ticketService.updateTicket(t);
                        }
                    }
                }
                submitterService.deleteSubmitter(submitter);
                break;
        }
        existingUser.setPermission(permissionService.getAdminPermission());
        userService.updateUser(existingUser);

        return existingUser;
    }

    @Override
    public User grantProjectManagerPermissionToExistingUser(User existingUser) {
        String currentPermissionLevel = existingUser.getPermission().getPermissionLevel();
        if (currentPermissionLevel.equals("ROLE_PM")) return existingUser;

        switch (currentPermissionLevel) {
            case ("ROLE_ADMIN"):
                ProjectManager pmFromAdmin = new ProjectManager();
                pmFromAdmin.setFullName(existingUser.getFullName());
                pmFromAdmin.setEmail(existingUser.getEmail());
                projectManagerService.saveProjectManager(pmFromAdmin);
                break;
            case ("ROLE_DEV"):
                Developer developer = developerService.getDeveloperByEmail(existingUser.getEmail());
                ProjectManager pmFromDev = new ProjectManager();
                pmFromDev.setFullName(developer.getFullName());
                pmFromDev.setEmail(developer.getEmail());
                projectManagerService.saveProjectManager(pmFromDev);

                List<Project> devProjects = projectService.getAllProjectsByDeveloper(developer);
                for (Project p : devProjects) {
                    p.getTeam().remove(developer);
                    projectService.updateProject(p);
                }
                List<Ticket> ticketsD = ticketService.getAllTickets();
                for (Ticket t : ticketsD) {
                    if (t.getAssignee() != null) {
                        if (t.getAssignee().getEmail().equals(developer.getEmail())) {
                            t.setAssignee(null);
                            ticketService.updateTicket(t);
                        }
                    }
                }
                developerService.deleteDeveloper(developer);
                break;
            case ("ROLE_SUB"):
                Submitter submitter = submitterService.getSubmitterByEmail(existingUser.getEmail());
                ProjectManager pmFromSub = new ProjectManager();
                pmFromSub.setFullName(submitter.getFullName());
                pmFromSub.setEmail(submitter.getEmail());
                projectManagerService.saveProjectManager(pmFromSub);

                List<Project> subProjects = projectService.getAllProjectsBySubmitter(submitter);
                for (Project p : subProjects) {
                    p.getSubmitters().remove(submitter);
                    projectService.updateProject(p);
                }
                List<Ticket> ticketsS = ticketService.getAllTickets();
                for (Ticket t : ticketsS) {
                    if (t.getOwner() != null) {
                        if (t.getOwner().getEmail().equals(submitter.getEmail())) {
                            t.setOwner(null);
                            ticketService.updateTicket(t);
                        }
                    }
                }
                submitterService.deleteSubmitter(submitter);
                break;
        }
        existingUser.setPermission(permissionService.getProjectManagerPermission());
        userService.updateUser(existingUser);

        return existingUser;
    }

    @Override
    public User grantDeveloperPermissionToExistingUser(User existingUser) {
        String currentPermissionLevel = existingUser.getPermission().getPermissionLevel();
        if (currentPermissionLevel.equals("ROLE_DEV")) return existingUser;

        switch (currentPermissionLevel) {
            case ("ROLE_ADMIN"):
                Developer devFromAdmin = new Developer();
                devFromAdmin.setFullName(existingUser.getFullName());
                devFromAdmin.setEmail(existingUser.getEmail());
                developerService.saveDeveloper(devFromAdmin);
                break;
            case ("ROLE_PM"):
                ProjectManager pm = projectManagerService.getProjectManagerByEmail(existingUser.getEmail());
                Developer devFromPm = new Developer();
                devFromPm.setFullName(pm.getFullName());
                devFromPm.setEmail(pm.getEmail());
                developerService.saveDeveloper(devFromPm);

                List<Project> pmProjects = projectService.getAllProjectsByProjectManagerId(pm.getId());
                for (Project p : pmProjects) {
                    p.setProjectManager(null);
                    projectService.updateProject(p);
                }
                projectManagerService.deleteProjectManager(pm);
                break;
            case ("ROLE_SUB"):
                Submitter submitter = submitterService.getSubmitterByEmail(existingUser.getEmail());
                Developer devFromSub = new Developer();
                devFromSub.setFullName(submitter.getFullName());
                devFromSub.setEmail(submitter.getEmail());
                developerService.saveDeveloper(devFromSub);

                List<Project> subProjects = projectService.getAllProjectsBySubmitter(submitter);
                for (Project p : subProjects) {
                    p.getSubmitters().remove(submitter);
                    projectService.updateProject(p);
                }
                List<Ticket> ticketsS = ticketService.getAllTickets();
                for (Ticket t : ticketsS) {
                    if (t.getOwner() != null) {
                        if (t.getOwner().getEmail().equals(submitter.getEmail())) {
                            t.setOwner(null);
                            ticketService.updateTicket(t);
                        }
                    }
                }
                submitterService.deleteSubmitter(submitter);
                break;
        }
        existingUser.setPermission(permissionService.getDeveloperPermission());
        userService.updateUser(existingUser);

        return existingUser;
    }

    @Override
    public User grantSubmitterPermissionToExistingUser(User existingUser) {
        String currentPermissionLevel = existingUser.getPermission().getPermissionLevel();
        if (currentPermissionLevel.equals("ROLE_SUB")) return existingUser;

        switch (currentPermissionLevel) {
            case ("ROLE_ADMIN"):
                Submitter subFromAdmin = new Submitter();
                subFromAdmin.setFullName(existingUser.getFullName());
                subFromAdmin.setEmail(existingUser.getEmail());
                submitterService.saveSubmitter(subFromAdmin);
                break;
            case ("ROLE_PM"):
                ProjectManager pm = projectManagerService.getProjectManagerByEmail(existingUser.getEmail());
                Submitter subFromPm = new Submitter();
                subFromPm.setFullName(pm.getFullName());
                subFromPm.setEmail(pm.getEmail());
                submitterService.saveSubmitter(subFromPm);

                List<Project> pmProjects = projectService.getAllProjectsByProjectManagerId(pm.getId());
                for (Project p : pmProjects) {
                    p.setProjectManager(null);
                    projectService.updateProject(p);
                }
                projectManagerService.deleteProjectManager(pm);
                break;
            case ("ROLE_DEV"):
                Developer developer = developerService.getDeveloperByEmail(existingUser.getEmail());
                Submitter subFromDev = new Submitter();
                subFromDev.setFullName(developer.getFullName());
                subFromDev.setEmail(developer.getEmail());
                submitterService.saveSubmitter(subFromDev);

                List<Project> devProjects = projectService.getAllProjectsByDeveloper(developer);
                for (Project p : devProjects) {
                    p.getTeam().remove(developer);
                    projectService.updateProject(p);
                }
                List<Ticket> ticketsD = ticketService.getAllTickets();
                for (Ticket t : ticketsD) {
                    if (t.getAssignee() != null) {
                        if (t.getAssignee().getEmail().equals(developer.getEmail())) {
                            t.setAssignee(null);
                            ticketService.updateTicket(t);
                        }
                    }
                }
                developerService.deleteDeveloper(developer);
                break;
        }
        existingUser.setPermission(permissionService.getSubmitterPermission());
        userService.updateUser(existingUser);

        return existingUser;
    }
}
