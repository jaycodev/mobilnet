package com.mobilnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
    List<Plan> findAllByOrderByIdDesc();

    @Query("""
        SELECT p FROM Plan p
        WHERE (:isActive IS NULL OR p.isActive = :isActive)
        ORDER BY p.id DESC
    """)
    List<Plan> findAllWithFilter(@Param("isActive") Boolean isActive);

    @Query("""
        SELECT p FROM Plan p
        LEFT JOIN p.ruc10Records r
        GROUP BY p.id, p.description, p.isActive
        ORDER BY COUNT(r) DESC
    """)
    List<Plan> findMostRegisteredPlans();
}
