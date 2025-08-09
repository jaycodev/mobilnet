package com.mobilnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.RecordState;

@Repository
public interface RecordStateRepository extends JpaRepository<RecordState, Integer> {
}
