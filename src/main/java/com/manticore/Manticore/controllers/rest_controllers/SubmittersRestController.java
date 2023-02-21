package com.manticore.Manticore.controllers.rest_controllers;

import com.manticore.Manticore.services.SubmitterService;
import com.manticore.Manticore.user_dtos.SubmitterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/sub", produces = "application/json")
@RequiredArgsConstructor
public class SubmittersRestController {

    private final SubmitterService submitterService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<SubmitterDto> getSubmittersList() {
        return submitterService.getAllSubmitterDtos();
    }
}
