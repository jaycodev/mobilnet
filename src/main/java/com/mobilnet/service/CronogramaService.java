package com.mobilnet.service;

import java.util.List;

import com.mobilnet.model.Cronograma;

public interface CronogramaService {
    Cronograma crearCronograma(Cronograma cronograma);
    List<Cronograma> listarCronograma();
    Cronograma buscarPorId(Integer idCronograma);
    Cronograma actualizarCronograma(Cronograma cronograma);
    boolean eliminarCronograma(Integer idCronograma);
}
