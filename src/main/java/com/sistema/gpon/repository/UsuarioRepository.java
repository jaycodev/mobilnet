package com.sistema.gpon.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistema.gpon.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	List<Usuario> findByRolDescripcion(String descripcion);
	
	@Query("""
		    SELECT u FROM Usuario u
		    WHERE (:idRol IS NULL OR u.rol.idRol = :idRol)
		    ORDER BY u.activo DESC, u.idUsuario DESC
		""")
		List<Usuario> findAllWithFilter(@Param("idRol") Integer idRol);

	Usuario findByCorreoAndContrasena(String correo, String contrasena);
}
