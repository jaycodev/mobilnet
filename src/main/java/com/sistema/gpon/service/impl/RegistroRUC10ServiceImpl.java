package com.sistema.gpon.service.impl;

import java.util.List;
import java.util.Optional;

import com.sistema.gpon.service.RegistroRUC10Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.gpon.model.RegistroRUC10;
import com.sistema.gpon.repository.RegistroRUC10Repository;

@Service
public class RegistroRUC10ServiceImpl implements RegistroRUC10Service {

	@Autowired
	private RegistroRUC10Repository registroRUC10Repository;

	@Override
	public RegistroRUC10 crearRegistro(RegistroRUC10 registro) {
		return registroRUC10Repository.save(registro);
	}

	@Override
	public List<RegistroRUC10> listarRegistros() {
		return registroRUC10Repository.findAll();
	}

	@Override
	public RegistroRUC10 buscarPorId(Integer idRegistro) {
		Optional<RegistroRUC10> optional = registroRUC10Repository.findById(idRegistro);
		return optional.orElse(null);
	}

	@Override
	public RegistroRUC10 actualizarRegistro(RegistroRUC10 registro) {
		if (registro.getIdRegistro() != null && registroRUC10Repository.existsById(registro.getIdRegistro())) {
			return registroRUC10Repository.save(registro);
		}
		return null;
	}

	@Override
	public boolean eliminarRegistro(Integer idRegistro) {
		if (registroRUC10Repository.existsById(idRegistro)) {
			registroRUC10Repository.deleteById(idRegistro);
			return true;
		}
		return false;
	}
}
