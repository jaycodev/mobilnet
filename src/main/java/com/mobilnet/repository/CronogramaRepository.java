package com.mobilnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.Cronograma;

@Repository
public interface CronogramaRepository extends JpaRepository<Cronograma, Integer> {
}
