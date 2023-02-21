package com.manticore.Manticore.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminProfilePage() {
        return "/pages/admin-profile";
    }

    @GetMapping("/pm")
    @PreAuthorize("hasRole('PM')")
    public String getProjectManagerProfilePage() {
        return "/pages/pm-profile";
    }

    @GetMapping("/dev")
    @PreAuthorize("hasRole('DEV')")
    public String getDeveloperProfilePage() {
        return "/pages/dev-profile";
    }

    @GetMapping("/sub")
    @PreAuthorize("hasRole('SUB')")
    public String getSubmitterProfilePage() {
        return "/pages/sub-profile";
    }
}
