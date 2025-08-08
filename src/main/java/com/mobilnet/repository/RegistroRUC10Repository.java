package com.mobilnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mobilnet.dto.DistritoCantidadDTO;
import com.mobilnet.dto.RegistroPorMesDTO;
import com.mobilnet.model.RegistroRUC10;

import java.util.List;

@Repository
public interface RegistroRUC10Repository extends JpaRepository<RegistroRUC10, Integer> {
	List<RegistroRUC10> findAllByOrderByIdRegistroDesc();

	@Query("""
		SELECT r FROM RegistroRUC10 r
		WHERE (:idEstado IS NULL OR r.estado.idEstado = :idEstado)
		ORDER BY r.idRegistro DESC
	""")
	List<RegistroRUC10> findAllWithFilter(@Param("idEstado") Integer idEstado);

	@Query("SELECT COUNT(r) FROM RegistroRUC10 r WHERE r.estado.descripcion = :descripcion")
	long countByEstadoDescripcion(@Param("descripcion") String descripcion);

	@Query("""
		SELECT MONTH(c.fechaRegistro) AS mes, COUNT(r) AS cantidad
		FROM RegistroRUC10 r
		JOIN r.cronograma c
		WHERE c.fechaRegistro IS NOT NULL
		GROUP BY MONTH(c.fechaRegistro)
		ORDER BY mes
	""")
	List<RegistroPorMesDTO> contarRegistrosPorMes();

	@Query("""
		SELECT r.distrito.nombreDistrito, COUNT(r)
		FROM RegistroRUC10 r
		GROUP BY r.distrito.nombreDistrito
		ORDER BY COUNT(r) DESC
	""")
	List<DistritoCantidadDTO> contarRegistrosPorDistrito();
}
