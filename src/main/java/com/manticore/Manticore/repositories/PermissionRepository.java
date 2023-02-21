package com.manticore.Manticore.repositories;

import com.manticore.Manticore.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findPermissionByPermissionLevel(String permissionLevel);
}
