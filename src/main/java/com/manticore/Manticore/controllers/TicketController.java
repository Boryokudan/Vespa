package com.manticore.Manticore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/tickets")
@RequiredArgsConstructor
public class TicketController {

    @GetMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public String getTicketDetailsPage(@PathVariable ("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "/pages/ticket-details";
    }

    @GetMapping("/project/{id}")
    @PreAuthorize("isAuthenticated()")
    public String getTicketsByProjectId(@PathVariable ("id") Long projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "/pages/project-tickets";
    }

    @GetMapping("/manage")
    @PreAuthorize("hasAnyRole('ADMIN', 'PM')")
    public String manageTickets() {
        return "/pages/manage-tickets";
    }

    @GetMapping("/my-tickets")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEV')")
    public String getTickets() {
        return "/pages/my-tickets";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUB')")
    public String addTicket(@RequestParam(name = "project", required = false) Long projectId, Model model) {
        if (projectId != null) model.addAttribute("projectId", projectId);
        return "/pages/add-new-ticket";
    }
}
