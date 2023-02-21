package com.manticore.Manticore.repositories;

import com.manticore.Manticore.models.user_models.ProjectManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ProjectManagerRepository extends JpaRepository<ProjectManager, Long> {
    ProjectManager findProjectManagerByEmail(String email);
    void deleteProjectManagerByEmail(String email);
}
