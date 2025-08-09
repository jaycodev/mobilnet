package com.mobilnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.model.Schedule;
import com.mobilnet.repository.ScheduleRepository;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Schedule create(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> list() {
        return scheduleRepository.findAll();
    }

    public Schedule findById(Integer scheduleId) {
        return scheduleRepository.findById(scheduleId).orElse(null);
    }

    public Schedule update(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public boolean delete(Integer scheduleId) {
        if (scheduleRepository.existsById(scheduleId)) {
            scheduleRepository.deleteById(scheduleId);
            return true;
        }
        return false;
    }
}
