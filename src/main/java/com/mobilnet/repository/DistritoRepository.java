package com.mobilnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.Distrito;

@Repository
public interface DistritoRepository extends JpaRepository<Distrito, Integer> {
}
