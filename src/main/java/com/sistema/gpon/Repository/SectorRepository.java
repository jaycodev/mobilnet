package com.sistema.gpon.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.gpon.model.Sector;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Integer> {
}
