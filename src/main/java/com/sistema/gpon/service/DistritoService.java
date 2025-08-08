package com.sistema.gpon.service;

import java.util.List;

import com.sistema.gpon.model.Distrito;

public interface DistritoService {
    List<Distrito> listarDistritos();
    Distrito buscarPorId(Integer idDistrito);
}
