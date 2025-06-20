package com.sistema.gpon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.gpon.model.Plan;
import org.springframework.stereotype.Repository;

@Repository

public interface PlanRepository extends JpaRepository<Plan, Integer> {
}
