package com.mobilnet.service;

import java.util.List;

import com.mobilnet.model.Rol;
import com.mobilnet.utils.ResultadoResponse;

public interface RolService {
	ResultadoResponse crearRol(Rol rol);
    List<Rol> listarRoles();
    Rol buscarPorId(Integer idRol);
    ResultadoResponse modificarRol(Rol rol);
    boolean eliminarRol(Integer idRol);
}
