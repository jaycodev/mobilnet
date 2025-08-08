package com.mobilnet.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	List<Usuario> findAllByOrderByIdUsuarioDesc();

	@Query("""
		SELECT u FROM Usuario u
		WHERE (:idRol IS NULL OR u.rol.idRol = :idRol)
		  AND (:activo IS NULL OR u.activo = :activo)
		ORDER BY u.idUsuario DESC
	""")
	List<Usuario> findAllWithFilter(
			@Param("idRol") Integer idRol,
			@Param("activo") Boolean activo
	);

	List<Usuario> findByRolDescripcion(String descripcion);
	Usuario findByCorreoAndContrasena(String correo, String contrasena);
}
