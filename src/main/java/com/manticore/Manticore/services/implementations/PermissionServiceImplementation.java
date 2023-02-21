package com.manticore.Manticore.services.implementations;

import com.manticore.Manticore.models.Permission;
import com.manticore.Manticore.repositories.PermissionRepository;
import com.manticore.Manticore.services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImplementation implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Override
    public Permission getPermissionById(Long id) {
        return permissionRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Permission> getPermissionsList() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getAdminPermission() {
        return permissionRepository.findPermissionByPermissionLevel("ROLE_ADMIN");
    }

    @Override
    public Permission getDeveloperPermission() {
        return permissionRepository.findPermissionByPermissionLevel("ROLE_DEV");
    }

    @Override
    public Permission getProjectManagerPermission() {
        return permissionRepository.findPermissionByPermissionLevel("ROLE_PM");
    }

    @Override
    public Permission getSubmitterPermission() {
        return permissionRepository.findPermissionByPermissionLevel("ROLE_SUB");
    }
}
