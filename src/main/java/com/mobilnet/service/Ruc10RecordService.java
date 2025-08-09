package com.mobilnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.dto.DistrictQuantityDTO;
import com.mobilnet.dto.RegistrationFilter;
import com.mobilnet.dto.RegistrationByMonthDTO;
import com.mobilnet.model.Ruc10Record;
import com.mobilnet.repository.Ruc10RecordRepository;

@Service
public class Ruc10RecordService {

    @Autowired
    private Ruc10RecordRepository ruc10RecordRepository;

    public Ruc10Record create(Ruc10Record record) {
        return ruc10RecordRepository.save(record);
    }

    public List<Ruc10Record> list() {
        return ruc10RecordRepository.findAllByOrderByIdDesc();
    }

    public List<Ruc10Record> listWithFilters(RegistrationFilter filter) {
        return ruc10RecordRepository.findAllWithFilter(filter.getStateId());
    }

    public Ruc10Record findById(Integer recordId) {
        return ruc10RecordRepository.findById(recordId).orElseThrow(null);
    }

    public Ruc10Record update(Ruc10Record record) {
        if (record.getId() != null && ruc10RecordRepository.existsById(record.getId())) {
            return ruc10RecordRepository.save(record);
        }
        return null;
    }

    public boolean delete(Integer recordId) {
        if (ruc10RecordRepository.existsById(recordId)) {
            ruc10RecordRepository.deleteById(recordId);
            return true;
        }
        return false;
    }

    public long countByState(String state) {
        return ruc10RecordRepository.countByStateDescription(state);
    }

    public List<RegistrationByMonthDTO> countByMonth() {
        return ruc10RecordRepository.countRegistrationsByMonth();
    }

    public List<DistrictQuantityDTO> countByDistrict() {
        return ruc10RecordRepository.countRegistrationsByDistrict();
    }
}
