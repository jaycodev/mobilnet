package com.mobilnet.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByOrderByIdDesc();

    @Query("""
        SELECT u FROM User u
        WHERE (:roleId IS NULL OR u.role.id = :roleId)
          AND (:isActive IS NULL OR u.isActive = :isActive)
        ORDER BY u.id DESC
    """)
    List<User> findAllWithFilter(
            @Param("roleId") Integer roleId,
            @Param("isActive") Boolean isActive
    );

    List<User> findByRoleDescription(String description);

    User findByEmailAndPassword(String email, String password);
}
