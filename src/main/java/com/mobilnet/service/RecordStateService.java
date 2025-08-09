package com.mobilnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.model.RecordState;
import com.mobilnet.repository.RecordStateRepository;

@Service
public class RecordStateService {

    @Autowired
    private RecordStateRepository recordStateRepository;

    public RecordState create(RecordState recordState) {
        return recordStateRepository.save(recordState);
    }

    public List<RecordState> list() {
        return recordStateRepository.findAll();
    }

    public RecordState findById(Integer stateId) {
        return recordStateRepository.findById(stateId)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado con ID: " + stateId));
    }

    public RecordState update(RecordState recordState) {
        if (!recordStateRepository.existsById(recordState.getId())) {
            throw new RuntimeException("No existe el estado con ID: " + recordState.getId());
        }
        return recordStateRepository.save(recordState);
    }

    public boolean delete(Integer stateId) {
        if (recordStateRepository.existsById(stateId)) {
            recordStateRepository.deleteById(stateId);
            return true;
        }
        return false;
    }
}
