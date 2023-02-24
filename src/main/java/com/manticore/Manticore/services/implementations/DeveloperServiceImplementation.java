package com.manticore.Manticore.services.implementations;

import com.manticore.Manticore.mappers.DeveloperMapper;
import com.manticore.Manticore.registration_forms.DeveloperRegistrationForm;
import com.manticore.Manticore.repositories.DeveloperRepository;
import com.manticore.Manticore.services.DeveloperService;
import com.manticore.Manticore.services.PermissionService;
import com.manticore.Manticore.services.UserService;
import com.manticore.Manticore.dtos.user_dtos.DeveloperDto;
import com.manticore.Manticore.models.user_models.Developer;
import com.manticore.Manticore.models.user_models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeveloperServiceImplementation implements DeveloperService {

    private final UserService userService;
    private final PermissionService permissionService;
    private final DeveloperRepository developerRepository;

    private final DeveloperMapper developerMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Developer getDeveloperByEmail(String email) {
        return developerRepository.findDeveloperByEmail(email);
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return developerRepository.findAll();
    }

    @Override
    public Developer registerDeveloper(DeveloperRegistrationForm registrationForm) {
        if (registrationForm.getPassword().trim().equals(registrationForm.getConfirmPassword().trim())) {
            boolean userExists = userService.getUserByEmail(registrationForm.getEmail()) != null;
            if (!userExists) {
                User newUser = new User();
                newUser.setFullName(registrationForm.getFullName());
                newUser.setEmail(registrationForm.getEmail());
                newUser.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
                newUser.setPermission(permissionService.getDeveloperPermission()); // GRANT DEV PERMISSIONS
                userService.registerUser(newUser);

                Developer newDeveloper = new Developer();
                newDeveloper.setFullName(registrationForm.getFullName());
                newDeveloper.setEmail(registrationForm.getEmail());
                newDeveloper.setSpecialization(registrationForm.getSpecialization());
                newDeveloper.setGrade(registrationForm.getGrade());
                return developerRepository.save(newDeveloper);
            }
        }
        return null;
    }

    @Override
    public Developer saveDeveloper(Developer developer) {
        return developerRepository.save(developer);
    }

    @Override
    public void deleteDeveloper(Developer developer) {
        developerRepository.delete(developer);
    }

    // <------------- Data Transfer Object Methods ------------->
    @Override
    public DeveloperDto getDeveloperDtoByEmail(String email) {
        return developerMapper.toDto(getDeveloperByEmail(email));
    }

    @Override
    public List<DeveloperDto> getAllDeveloperDtos() {
        return developerMapper.toDtoList(getAllDevelopers());
    }
}
