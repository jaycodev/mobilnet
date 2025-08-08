package com.mobilnet.service;

import java.util.List;

import com.mobilnet.model.Distrito;

public interface DistritoService {
    List<Distrito> listarDistritos();
    Distrito buscarPorId(Integer idDistrito);
}
