package com.mobilnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
}
