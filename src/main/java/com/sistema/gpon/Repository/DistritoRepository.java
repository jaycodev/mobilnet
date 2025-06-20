package com.sistema.gpon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.gpon.model.Distrito;
import org.springframework.stereotype.Repository;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Integer> {
}
