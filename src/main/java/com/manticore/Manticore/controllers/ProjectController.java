package com.manticore.Manticore.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/projects")
public class ProjectController {

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public String projectDetailsPage(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("id", id);
        return "pages/project-details";
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public String projectsPage() {
        return "pages/projects";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProjectPage() {
        return "pages/add-new-project";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editProjectPage(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("id", id);
        return "pages/edit-project";
    }
}
