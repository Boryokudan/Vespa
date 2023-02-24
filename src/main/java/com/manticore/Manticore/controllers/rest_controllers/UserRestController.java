package com.manticore.Manticore.controllers.rest_controllers;

import com.manticore.Manticore.services.UserPermissionManagementService;
import com.manticore.Manticore.services.UserService;
import com.manticore.Manticore.dtos.user_dtos.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users", produces = "application/json")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private final UserPermissionManagementService userPermissionManagementService;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto getCurrentUserProfileData() {
        return userService.getAdminProfileData();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> getUsersList() {
        return userService.getAllUserDtos();
    }
    @GetMapping(params = "sort=name")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> getUsersListSortedByFullName() {
        return userService.getAllUserDtosSortedByFullName();
    }

    @GetMapping(params = "sort=email")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> getUsersListSortedByEmail() {
        return userService.getAllUserDtosSortedByEmail();
    }

    @GetMapping(params = "sort=role")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> getUsersListSortedByRole() {
        return userService.getAllUserDtosSortedByRole();
    }

    @PutMapping(params="update=roles")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> updateUsersRoles(@RequestBody List<UserDto> userDtos) {
        return userPermissionManagementService.updateUserDtosRoles(userDtos);
    }
}
