package com.mobilnet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.model.Distrito;
import com.mobilnet.repository.DistritoRepository;
import com.mobilnet.service.DistritoService;

@Service
public class DistritoServiceImpl implements DistritoService {

	@Autowired
	private DistritoRepository distritoRepository;

	@Override
	public List<Distrito> listarDistritos() {
		return distritoRepository.findAll();
	}

	@Override
	public Distrito buscarPorId(Integer idDistrito) {
		return distritoRepository.findById(idDistrito).orElseThrow(null);
	}
}
