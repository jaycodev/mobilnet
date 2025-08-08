package com.mobilnet.service;

import java.util.List;

import com.mobilnet.model.EstadoRegistro;

public interface EstadoRegistroService {
    EstadoRegistro crearEstado(EstadoRegistro estadoRegistro);
    List<EstadoRegistro> listarEstados();
    EstadoRegistro buscarPorId(Integer idEstado);
    EstadoRegistro actualizarEstado(EstadoRegistro Estado);
    boolean eliminarEstado(Integer idEstado);
}
