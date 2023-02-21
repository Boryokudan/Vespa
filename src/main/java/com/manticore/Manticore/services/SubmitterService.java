package com.manticore.Manticore.services;

import com.manticore.Manticore.registration_forms.SubmitterRegistrationForm;
import com.manticore.Manticore.user_dtos.SubmitterDto;
import com.manticore.Manticore.models.user_models.Submitter;

import java.util.List;

public interface SubmitterService {
    Submitter getSubmitterByEmail(String email);
    List<Submitter> getAllSubmitters();
    Submitter registerSubmitter(SubmitterRegistrationForm registrationForm);
    Submitter saveSubmitter(Submitter submitter);
    void deleteSubmitter(Submitter submitter);

    // <------------- Data Transfer Object Methods ------------->
    SubmitterDto getSubmitterDtoByEmail(String email);
    List<SubmitterDto> getAllSubmitterDtos();
}
