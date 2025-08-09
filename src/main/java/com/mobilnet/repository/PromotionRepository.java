package com.mobilnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer> {
    List<Promotion> findAllByOrderByIdDesc();

    @Query("""
        SELECT p FROM Promotion p
        WHERE (:isActive IS NULL OR p.isActive = :isActive)
        ORDER BY p.id DESC
    """)
    List<Promotion> findAllWithFilter(@Param("isActive") Boolean isActive);
}
