package com.mobilnet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.model.Sector;
import com.mobilnet.repository.SectorRepository;
import com.mobilnet.service.SectorService;

@Service
public class SectorServiceImpl implements SectorService {

	@Autowired
	private SectorRepository sectorRepository;

	@Override
	public List<Sector> listarSectores() {
		return sectorRepository.findAll();
	}
}
