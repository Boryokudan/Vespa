package com.manticore.Manticore.repositories;

import com.manticore.Manticore.models.user_models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE " +
            "LOWER(u.fullName) LIKE %:query% OR " +
            "LOWER(u.email) LIKE %:query% OR " +
            "LOWER(u.permission.roleName) LIKE %:query%")
    List<User> processSearchQuery(@Param("query") String query, Sort sort);
}
