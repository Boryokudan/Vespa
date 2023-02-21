package com.manticore.Manticore.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public String indexPage() {
        return "pages/projects";
    }

    @GetMapping("/sign-in")
    public String signInPage() {
        return "pages/sign-in";
    }

    @GetMapping("/manage-roles")
    @PreAuthorize("hasRole('ADMIN')")
    public String manageRoles() {
        return "pages/manage-roles";
    }
}
