package com.mobilnet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.model.Rol;
import com.mobilnet.repository.RolRepository;
import com.mobilnet.service.RolService;
import com.mobilnet.utils.ResultadoResponse;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public ResultadoResponse crearRol(Rol rol) {
        try {
            Rol registrado = rolRepository.save(rol);

            String mensaje = String.format("Rol registrado correctamente", registrado.getIdRol());
            return new ResultadoResponse(true, mensaje);

        }catch (Exception ex) {
            ex.printStackTrace();
            return new ResultadoResponse(false, "Error al registrar: " + ex.getMessage());
        }
    }

    @Override
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    @Override
    public Rol buscarPorId(Integer idRol) {
        return rolRepository.findById(idRol).orElseThrow();
    }

    @Override
    public ResultadoResponse modificarRol(Rol rol) {
        try {
            Rol actualizado = rolRepository.save(rol);

            String mensaje = String.format("Rol actualizado correctamente", actualizado.getIdRol());
            return new ResultadoResponse(true, mensaje);

        } catch (Exception ex) {
            return new ResultadoResponse(false, "Error al actualizar: " + ex.getMessage());
        }
    }

    @Override
    public boolean eliminarRol(Integer idRol) {
        try {
            if (rolRepository.existsById(idRol)) {
                rolRepository.deleteById(idRol);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
