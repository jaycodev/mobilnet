package com.mobilnet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.model.EstadoRegistro;
import com.mobilnet.repository.EstadoRepository;
import com.mobilnet.service.EstadoRegistroService;

import java.util.List;

@Service
public class EstadoRegistroServiceImpl implements EstadoRegistroService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public EstadoRegistro crearEstado(EstadoRegistro estadoRegistro) {
        return estadoRepository.save(estadoRegistro);
    }

    @Override
    public List<EstadoRegistro> listarEstados() {
        return estadoRepository.findAll();
    }

    @Override
    public EstadoRegistro buscarPorId(Integer idEstado) {
        return estadoRepository.findById(idEstado)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado con ID: " + idEstado));
    }

    @Override
    public EstadoRegistro actualizarEstado(EstadoRegistro estado) {
        if (!estadoRepository.existsById(estado.getIdEstado())) {
            throw new RuntimeException("No existe el estado con ID: " + estado.getIdEstado());
        }
        return estadoRepository.save(estado);
    }

    @Override
    public boolean eliminarEstado(Integer idEstado) {
        if (estadoRepository.existsById(idEstado)) {
            estadoRepository.deleteById(idEstado);
            return true;
        }
        return false;
    }
}
