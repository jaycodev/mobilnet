package com.sistema.gpon.service.impl;

import java.util.List;

import com.sistema.gpon.service.UsuarioService;
import com.sistema.gpon.utils.ResultadoResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.sistema.gpon.dto.UsuarioFilter;
import com.sistema.gpon.model.Usuario;
import com.sistema.gpon.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ResultadoResponse crearUsuario(Usuario usuario) {
        try {
            usuario.setActivo(true);

            Usuario registrado = usuarioRepository.save(usuario);

            String nombreCompleto = registrado.getNombre() + " " + registrado.getApellido();
            String mensaje = String.format("El usuario %s (Cod. %s) ha sido registrado exitosamente.",
                    nombreCompleto, registrado.getIdUsuario());

            return new ResultadoResponse(true, mensaje);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultadoResponse(false, "Ocurrió un error al registrar el usuario: " + ex.getMessage());
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> listarFiltros(UsuarioFilter filtro) {
        return usuarioRepository.findAllWithFilter(filtro.getIdRol(), filtro.getActivo());
    }

    @Override
    public ResultadoResponse modificarUsuario(Usuario usuario) {
        try {
            Usuario actualizado = usuarioRepository.save(usuario);

            String nombreCompleto = actualizado.getNombre() + " " + actualizado.getApellido();
            String mensaje = String.format("Los datos del usuario %s (Cod. %s) han sido actualizados correctamente.",
                    nombreCompleto, actualizado.getIdUsuario());

            return new ResultadoResponse(true, mensaje);

        } catch (Exception ex) {
            return new ResultadoResponse(false, "Ocurrió un error al actualizar el usuario: " + ex.getMessage());
        }
    }

    @Override
    public List<Usuario> findByRolDescripcion(String descripcion) {
        return usuarioRepository.findByRolDescripcion(descripcion);
    }

    @Override
    public Usuario buscarPorId(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario).orElseThrow();
    }

    @Override
    public ResultadoResponse cambiarEstado(Integer id) {
        Usuario usuario = this.buscarPorId(id);
        boolean accion = !usuario.getActivo();

        String texto = accion ? "activado" : "desactivado";

        usuario.setActivo(accion);

        try {
            Usuario registrado = usuarioRepository.save(usuario);

            String nombreCompleto = registrado.getNombre() + " " + registrado.getApellido();
            String mensaje = String.format("El usuario %s (Cod. %s) ha sido %s correctamente.",
                    nombreCompleto, registrado.getIdUsuario(), texto);
            return new ResultadoResponse(true, mensaje);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultadoResponse(false, "Ocurrió un error al cambiar el estado del usuario: " + ex.getMessage());
        }
    }

    @Override
    public Usuario autenticacion(Usuario filter) {
        return usuarioRepository.findByCorreoAndContrasena(filter.getCorreo(), filter.getContrasena());
    }
}