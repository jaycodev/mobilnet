package com.mobilnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobilnet.dto.UserFilter;
import com.mobilnet.model.User;
import com.mobilnet.repository.UserRepository;
import com.mobilnet.utils.ResultResponse;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResultResponse create(User user) {
        try {
            user.setIsActive(true);

            User savedUser = userRepository.save(user);

            String fullName = savedUser.getFirstName() + " " + savedUser.getLastName();
            String message = String.format(
                    "El usuario %s (Cod. %s) ha sido registrado exitosamente.",
                    fullName, savedUser.getId());

            return new ResultResponse(true, message);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultResponse(false, "Ocurrió un error al registrar el usuario: " + ex.getMessage());
        }
    }

    public List<User> list() {
        return userRepository.findAllByOrderByIdDesc();
    }

    public List<User> listWithFilters(UserFilter filter) {
        return userRepository.findAllWithFilter(filter.getRoleId(), filter.getIsActive());
    }

    public ResultResponse update(User user) {
        try {
            User existing = userRepository.findById(user.getId()).orElse(null);

            if (existing == null) {
                return new ResultResponse(false, "El usuario no existe en la base de datos.");
            }

            user.setPassword(existing.getPassword());

            User updatedUser = userRepository.save(user);

            String fullName = updatedUser.getFirstName() + " " + updatedUser.getLastName();
            String message = String.format(
                    "Los datos del usuario %s (Cod. %s) han sido actualizados correctamente.",
                    fullName, updatedUser.getId());

            return new ResultResponse(true, message);

        } catch (Exception ex) {
            return new ResultResponse(false, "Ocurrió un error al actualizar el usuario: " + ex.getMessage());
        }
    }

    public List<User> findByRoleDescription(String description) {
        return userRepository.findByRoleDescription(description);
    }

    public User findById(Integer userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    public ResultResponse changeStatus(Integer id) {
        User user = this.findById(id);
        boolean activate = !user.getIsActive();

        String statusText = activate ? "activado" : "desactivado";
        user.setIsActive(activate);

        try {
            User savedUser = userRepository.save(user);

            String fullName = savedUser.getFirstName() + " " + savedUser.getLastName();
            String message = String.format(
                    "El usuario %s (Cod. %s) ha sido %s correctamente.",
                    fullName, savedUser.getId(), statusText);

            return new ResultResponse(true, message);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResultResponse(false, "Ocurrió un error al cambiar el estado del usuario: " + ex.getMessage());
        }
    }

    public User authenticate(User user) {
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }
}
