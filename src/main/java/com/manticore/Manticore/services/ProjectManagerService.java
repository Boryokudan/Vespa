package com.manticore.Manticore.services;

import com.manticore.Manticore.registration_forms.ProjectManagerRegistrationForm;
import com.manticore.Manticore.user_dtos.ProjectManagerDto;
import com.manticore.Manticore.models.user_models.ProjectManager;

import java.util.List;

public interface ProjectManagerService {
    ProjectManager getProjectManagerByEmail(String email);
    List<ProjectManager> getAllProjectManagers();
    ProjectManager registerProjectManager(ProjectManagerRegistrationForm registrationForm);
    ProjectManager saveProjectManager(ProjectManager projectManager);
    void deleteProjectManagerByEmail(String email);
    void deleteProjectManager(ProjectManager projectManager);

    // <------------- Data Transfer Object Methods ------------->

    ProjectManagerDto getProjectManagerDtoByEmail(String email);
    List<ProjectManagerDto> getAllProjectManagerDtos();
}
