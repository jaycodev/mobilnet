package com.sistema.gpon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.gpon.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
	List<Cliente> findAllByOrderByDniClienteDesc();

	@Query("""
		SELECT c FROM Cliente c
		WHERE (:activo IS NULL OR c.activo = :activo)
		ORDER BY c.dniCliente DESC
	""")
	List<Cliente> findAllWithFilter(@Param("activo") Boolean activo);
}
