package com.mobilnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mobilnet.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
