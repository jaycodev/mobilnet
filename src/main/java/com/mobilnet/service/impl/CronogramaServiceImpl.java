package com.mobilnet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.model.Cronograma;
import com.mobilnet.repository.CronogramaRepository;
import com.mobilnet.service.CronogramaService;

import java.util.List;

@Service
public class CronogramaServiceImpl implements CronogramaService {

    @Autowired
    private CronogramaRepository cronogramaRepository;

    @Override
    public Cronograma crearCronograma(Cronograma cronograma) {
        return cronogramaRepository.save(cronograma);
    }

    @Override
    public List<Cronograma> listarCronograma() {
        return cronogramaRepository.findAll();
    }

    @Override
    public Cronograma buscarPorId(Integer idCronograma) {
        return cronogramaRepository.findById(idCronograma).orElse(null);
    }

    @Override
    public Cronograma actualizarCronograma(Cronograma cronograma) {
        return cronogramaRepository.save(cronograma);
    }

    @Override
    public boolean eliminarCronograma(Integer idCronograma) {
        if (cronogramaRepository.existsById(idCronograma)) {
            cronogramaRepository.deleteById(idCronograma);
            return true;
        }
        return false;
    }
}
