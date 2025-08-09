package com.mobilnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.model.Sector;
import com.mobilnet.repository.SectorRepository;

@Service
public class SectorService {
    @Autowired
    private SectorRepository sectorRepository;

    public List<Sector> list() {
        return sectorRepository.findAll();
    }
}
