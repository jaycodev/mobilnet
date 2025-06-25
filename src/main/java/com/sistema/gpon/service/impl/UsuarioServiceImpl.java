package com.sistema.gpon.service.impl;

import java.util.List;

import com.sistema.gpon.service.UsuarioService;
import com.sistema.gpon.utils.ResultadoResponse;

import org.springframework.beans.factory.annotation.Autowired;

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
			Usuario registrado = usuarioRepository.save(usuario);
			
			String mensaje = String.format("Usuario con numero %s registrado", registrado.getIdUsuario());		
			return new ResultadoResponse(true, mensaje);
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return new ResultadoResponse(false, "Error al registrar: " + ex.getMessage());
		}
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAllByOrderByIdUsuarioDesc();
	}

	@Override
	public Usuario buscarPorId(Integer idUsuario) {
		return usuarioRepository.findById(idUsuario).orElseThrow();
	}

	@Override
	public ResultadoResponse modificarUsuario(Usuario usuario) {
		try {
			Usuario actualizado = usuarioRepository.save(usuario);

			String mensaje = String.format("Usuario con nro. %s actualizado", actualizado.getIdUsuario());
			return new ResultadoResponse(true, mensaje);

		} catch (Exception ex) {
			return new ResultadoResponse(false, "Error al actualizar: " + ex.getMessage());
		}	
	}

	@Override
	public boolean eliminarUsuario(Integer idUsuario) {
		try {
	        if (usuarioRepository.existsById(idUsuario)) {
	        	usuarioRepository.deleteById(idUsuario);
	            return true;
	        } else {
	            return false;
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return false; 
	    }
	}
}
