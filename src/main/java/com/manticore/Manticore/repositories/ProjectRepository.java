package com.manticore.Manticore.repositories;

import com.manticore.Manticore.models.Project;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByProjectManagerId(Long pmId);

    @Query("SELECT p FROM Project p WHERE " +
            "LOWER(p.title) LIKE %:query% OR " +
            "LOWER(p.status) LIKE %:query%")
    List<Project> processSearchQuery(@Param("query") String query, Sort sort);
}
