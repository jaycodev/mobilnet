package com.mobilnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.model.Role;
import com.mobilnet.repository.RoleRepository;
import com.mobilnet.utils.ResultResponse;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public ResultResponse create(Role role) {
        try {
            Role registered = roleRepository.save(role);

            String message = String.format("Rol registrado correctamente", registered.getId());
            return new ResultResponse(true, message);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultResponse(false, "Error al registrar: " + ex.getMessage());
        }
    }

    public List<Role> list() {
        return roleRepository.findAll();
    }

    public Role findById(Integer roleId) {
        return roleRepository.findById(roleId).orElseThrow();
    }

    public ResultResponse update(Role role) {
        try {
            Role updated = roleRepository.save(role);

            String message = String.format("Rol actualizado correctamente", updated.getId());
            return new ResultResponse(true, message);

        } catch (Exception ex) {
            return new ResultResponse(false, "Error al actualizar: " + ex.getMessage());
        }
    }

    public boolean delete(Integer roleId) {
        try {
            if (roleRepository.existsById(roleId)) {
                roleRepository.deleteById(roleId);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
