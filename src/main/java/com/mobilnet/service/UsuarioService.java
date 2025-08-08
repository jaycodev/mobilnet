package com.mobilnet.service;

import java.util.List;

import com.mobilnet.dto.UsuarioFilter;
import com.mobilnet.model.Usuario;
import com.mobilnet.utils.ResultadoResponse;

public interface UsuarioService {
	ResultadoResponse crearUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
    List<Usuario> listarFiltros(UsuarioFilter filtro);
    Usuario buscarPorId(Integer idUsuario);
    ResultadoResponse modificarUsuario(Usuario usuario);
    List<Usuario> findByRolDescripcion(String descripcion);
    ResultadoResponse cambiarEstado(Integer Id);
    Usuario autenticacion(Usuario usuario);
}
