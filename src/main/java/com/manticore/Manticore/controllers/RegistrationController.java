package com.manticore.Manticore.controllers;

import com.manticore.Manticore.registration_forms.DeveloperRegistrationForm;
import com.manticore.Manticore.registration_forms.ProjectManagerRegistrationForm;
import com.manticore.Manticore.registration_forms.SubmitterRegistrationForm;
import com.manticore.Manticore.services.DeveloperService;
import com.manticore.Manticore.services.ProjectManagerService;
import com.manticore.Manticore.services.SubmitterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign-up")
@RequiredArgsConstructor
public class RegistrationController {

    private final DeveloperService developerService;
    private final ProjectManagerService projectManagerService;
    private final SubmitterService submitterService;

    @GetMapping
    @PreAuthorize("isAnonymous()")
    public String getSignUpSelectionPage() {
        return "pages/sign-up";
    }

    @GetMapping("/dev")
    @PreAuthorize("isAnonymous()")
    public String developerRegistrationForm() {
        return "pages/dev-sign-up";
    }

    @GetMapping("/pm")
    @PreAuthorize("isAnonymous()")
    public String projectManagerRegistrationForm() {
        return "pages/pm-sign-up";
    }

    @GetMapping("/sub")
    @PreAuthorize("isAnonymous()")
    public String submitterRegistrationForm() {
        return "pages/sub-sign-up";
    }

    @PostMapping("/dev")
    @PreAuthorize("isAnonymous()")
    public String processDeveloperRegistration(DeveloperRegistrationForm registrationForm) {
        if (developerService.registerDeveloper(registrationForm) != null) return "redirect:/sign-in";
        else return "redirect:/sign-up/dev?error";
    }

    @PostMapping("/pm")
    @PreAuthorize("isAnonymous()")
    public String processProjectManagerRegistration(ProjectManagerRegistrationForm registrationForm) {
        if (projectManagerService.registerProjectManager(registrationForm) != null) return "redirect:/sign-in";
        else return "redirect:/sign-up/pm?error";
    }

    @PostMapping("/sub")
    @PreAuthorize("isAnonymous()")
    public String processSubmitterRegistration(SubmitterRegistrationForm registrationForm) {
        if (submitterService.registerSubmitter(registrationForm) != null) return "redirect:/sign-in";
        else return "redirect:/sign-up/sub?error";
    }
}
