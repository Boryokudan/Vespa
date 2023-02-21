package com.manticore.Manticore.repositories;

import com.manticore.Manticore.models.user_models.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    Developer findDeveloperByEmail(String email);
}
