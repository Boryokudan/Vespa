package com.manticore.Manticore.services.implementations;

import com.manticore.Manticore.mappers.ProjectManagerMapper;
import com.manticore.Manticore.registration_forms.ProjectManagerRegistrationForm;
import com.manticore.Manticore.repositories.ProjectManagerRepository;
import com.manticore.Manticore.services.PermissionService;
import com.manticore.Manticore.services.ProjectManagerService;
import com.manticore.Manticore.services.UserService;
import com.manticore.Manticore.dtos.user_dtos.ProjectManagerDto;
import com.manticore.Manticore.models.user_models.ProjectManager;
import com.manticore.Manticore.models.user_models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectManagerServiceImplementation implements ProjectManagerService {
    private final UserService userService;
    private final PermissionService permissionService;
    private final ProjectManagerRepository projectManagerRepository;
    private final ProjectManagerMapper projectManagerMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ProjectManager getProjectManagerByEmail(String email) {
        return projectManagerRepository.findProjectManagerByEmail(email);
    }

    @Override
    public List<ProjectManager> getAllProjectManagers() {
        return projectManagerRepository.findAll();
    }

    @Override
    public ProjectManager registerProjectManager(ProjectManagerRegistrationForm registrationForm) {
        if (registrationForm.getPassword().trim().equals(registrationForm.getConfirmPassword().trim())) {
            boolean userExists = userService.getUserByEmail(registrationForm.getEmail()) != null;
            if (!userExists) {
                User newUser = new User();
                newUser.setFullName(registrationForm.getFullName());
                newUser.setEmail(registrationForm.getEmail());
                newUser.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
                newUser.setPermission(permissionService.getProjectManagerPermission()); // GRANT PM PERMISSIONS
                userService.registerUser(newUser);

                ProjectManager newProjectManager = new ProjectManager();
                newProjectManager.setFullName(registrationForm.getFullName());
                newProjectManager.setEmail(registrationForm.getEmail());
                return projectManagerRepository.save(newProjectManager);
            }
        }
        return null;
    }

    @Override
    public ProjectManager saveProjectManager(ProjectManager projectManager) {
        return projectManagerRepository.save(projectManager);
    }

    @Override
    public void deleteProjectManagerByEmail(String email) {
        projectManagerRepository.deleteProjectManagerByEmail(email);
    }

    @Override
    public void deleteProjectManager(ProjectManager projectManager) {
        projectManagerRepository.delete(projectManager);
    }

    @Override
    public ProjectManagerDto getProjectManagerDtoByEmail(String email) {
        return projectManagerMapper.toDto(getProjectManagerByEmail(email));
    }

    @Override
    public List<ProjectManagerDto> getAllProjectManagerDtos() {
        return projectManagerMapper.toDtoList(getAllProjectManagers());
    }
}
