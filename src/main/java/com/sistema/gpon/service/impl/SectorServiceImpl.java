package com.sistema.gpon.service.impl;

import java.util.List;

import com.sistema.gpon.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;

import com.sistema.gpon.model.Sector;
import com.sistema.gpon.repository.SectorRepository;
import org.springframework.stereotype.Service;

@Service
public class SectorServiceImpl implements SectorService {

	@Autowired
	private SectorRepository sectorRepository;

	@Override
	public List<Sector> listarSectores() {
		return sectorRepository.findAll();
	}
}
