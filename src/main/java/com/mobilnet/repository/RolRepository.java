package com.mobilnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
}
