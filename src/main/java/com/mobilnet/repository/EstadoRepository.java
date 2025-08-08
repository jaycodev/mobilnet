package com.mobilnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.EstadoRegistro;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoRegistro, Integer> {
}
