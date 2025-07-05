package com.sistema.gpon.repository;

import java.util.List;

import com.sistema.gpon.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.gpon.model.Promocion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Integer> {
	List<Promocion> findAllByOrderByIdPromocionDesc();

	@Query("""
		SELECT p FROM Promocion p
		WHERE (:activo IS NULL OR p.activo = :activo)
		ORDER BY p.idPromocion DESC
	""")
	List<Promocion> findAllWithFilter(@Param("activo") Boolean activo);
}
