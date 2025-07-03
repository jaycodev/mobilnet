package com.sistema.gpon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.gpon.model.Plan;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
	
	@Query("""
			SELECT p FROM Plan p 
			WHERE activo = true 
			ORDER BY activo ASC
			""")
	List<Plan>listarPlanesEstadoTrue();
}
