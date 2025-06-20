package com.sistema.gpon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.gpon.model.Rol;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
