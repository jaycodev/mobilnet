package com.mobilnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.model.District;
import com.mobilnet.repository.DistrictRepository;

@Service
public class DistrictService {

	@Autowired
	private DistrictRepository districtRepository;

	public List<District> list() {
		return districtRepository.findAll();
	}

	public District findById(Integer districtId) {
		return districtRepository.findById(districtId).orElseThrow(null);
	}
}
