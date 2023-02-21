package com.manticore.Manticore.repositories;

import com.manticore.Manticore.models.user_models.Submitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SubmitterRepository extends JpaRepository<Submitter, Long> {
    Submitter findSubmitterByEmail(String email);
}
