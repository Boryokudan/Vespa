package com.manticore.Manticore.services;

import com.manticore.Manticore.registration_forms.DeveloperRegistrationForm;
import com.manticore.Manticore.dtos.user_dtos.DeveloperDto;
import com.manticore.Manticore.models.user_models.Developer;

import java.util.List;

public interface DeveloperService {
    Developer getDeveloperByEmail(String email);
    List<Developer> getAllDevelopers();
    Developer registerDeveloper(DeveloperRegistrationForm registrationForm);
    Developer saveDeveloper(Developer developer);
    void deleteDeveloper(Developer developer);

    // <------------- Data Transfer Object Methods ------------->
    DeveloperDto getDeveloperDtoByEmail(String email);

    List<DeveloperDto> getAllDeveloperDtos();
}
