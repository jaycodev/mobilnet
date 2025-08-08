package com.mobilnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Integer> {
}
