package com.manticore.Manticore.services;

import com.manticore.Manticore.models.Permission;

import java.util.List;

public interface PermissionService {

    Permission getPermissionById(Long id);
    List<Permission> getPermissionsList();
    Permission getAdminPermission();
    Permission getDeveloperPermission();
    Permission getProjectManagerPermission();
    Permission getSubmitterPermission();
}
