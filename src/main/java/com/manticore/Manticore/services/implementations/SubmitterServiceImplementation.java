package com.manticore.Manticore.services.implementations;

import com.manticore.Manticore.mappers.SubmitterMapper;
import com.manticore.Manticore.registration_forms.SubmitterRegistrationForm;
import com.manticore.Manticore.repositories.SubmitterRepository;
import com.manticore.Manticore.services.PermissionService;
import com.manticore.Manticore.services.SubmitterService;
import com.manticore.Manticore.services.UserService;
import com.manticore.Manticore.user_dtos.SubmitterDto;
import com.manticore.Manticore.models.user_models.Submitter;
import com.manticore.Manticore.models.user_models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmitterServiceImplementation implements SubmitterService {

    private final UserService userService;
    private final PermissionService permissionService;
    private final SubmitterRepository submitterRepository;
    private final SubmitterMapper submitterMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Submitter getSubmitterByEmail(String email) {
        return submitterRepository.findSubmitterByEmail(email);
    }

    @Override
    public List<Submitter> getAllSubmitters() {
        return submitterRepository.findAll();
    }

    @Override
    public Submitter registerSubmitter(SubmitterRegistrationForm registrationForm) {
        if (registrationForm.getPassword().trim().equals(registrationForm.getConfirmPassword().trim())) {
            boolean userExists = userService.getUserByEmail(registrationForm.getEmail()) != null;
            if (!userExists) {
                User newUser = new User();
                newUser.setFullName(registrationForm.getFullName());
                newUser.setEmail(registrationForm.getEmail());
                newUser.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
                newUser.setPermission(permissionService.getSubmitterPermission()); // GRANT SUB PERMISSIONS
                userService.registerUser(newUser);

                Submitter newSubmitter = new Submitter();
                newSubmitter.setFullName(registrationForm.getFullName());
                newSubmitter.setEmail(registrationForm.getEmail());
                newSubmitter.setPosition(registrationForm.getPosition());
                newSubmitter.setGrade(registrationForm.getGrade());
                return submitterRepository.save(newSubmitter);
            }
        }
        return null;
    }

    @Override
    public Submitter saveSubmitter(Submitter submitter) {
        return submitterRepository.save(submitter);
    }

    @Override
    public void deleteSubmitter(Submitter submitter) {
        submitterRepository.delete(submitter);
    }

    // <------------- Data Transfer Object Methods ------------->
    @Override
    public SubmitterDto getSubmitterDtoByEmail(String email) {
        return submitterMapper.toDto(getSubmitterByEmail(email));
    }

    @Override
    public List<SubmitterDto> getAllSubmitterDtos() {
        return submitterMapper.toDtoList(getAllSubmitters());
    }
}
