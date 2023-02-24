package com.manticore.Manticore.services;

import com.manticore.Manticore.dtos.user_dtos.UserDto;
import com.manticore.Manticore.models.user_models.User;

import java.util.List;

public interface UserPermissionManagementService {

    List<UserDto> updateUserDtosRoles(List<UserDto> usersDtos);
    User grantAdminPermissionToExistingUser(User user);
    User grantProjectManagerPermissionToExistingUser(User user);
    User grantDeveloperPermissionToExistingUser(User user);
    User grantSubmitterPermissionToExistingUser(User user);
}
