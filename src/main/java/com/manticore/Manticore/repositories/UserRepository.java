package com.manticore.Manticore.repositories;

import com.manticore.Manticore.models.user_models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
