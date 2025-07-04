package com.sistema.gpon.repository;

import java.util.List;

import com.sistema.gpon.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.gpon.model.Plan;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
	@Query("""
		    SELECT p FROM Plan p
		    WHERE (:activo IS NULL OR p.activo = :activo)
		""")
	List<Plan> findAllWithFilter(@Param("activo") Boolean activo);
	
	
	@Query("""
		    SELECT p FROM Plan p
		  LEFT JOIN p.listRegistrosConPlanes r
		  GROUP BY p.idPlan, p.descripcion, p.activo
		  ORDER BY COUNT(r) DESC
		""")
List<Plan> findPlanesMasRegistrados();

}
