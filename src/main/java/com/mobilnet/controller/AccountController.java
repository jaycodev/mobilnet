package com.mobilnet.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mobilnet.dto.UserPasswordDTO;
import com.mobilnet.dto.UserProfileDTO;
import com.mobilnet.model.User;
import com.mobilnet.service.UserService;
import com.mobilnet.utils.Alert;
import com.mobilnet.utils.ResultResponse;

@Controller
@RequestMapping("/cuenta")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        User usuario = new User();
        usuario.setEmail("demo.admin@example.com");
        model.addAttribute("usuario", usuario);
        return "cuenta/login";
    }

    @PostMapping("/iniciarSesion")
    public String login(@ModelAttribute User user, HttpSession session, Model model,
            RedirectAttributes flash) {
        User loggedUser = userService.authenticate(user);

        if (loggedUser == null) {
            model.addAttribute("usuario", new User());

            String errorStart = Alert.sweetAlertError("El correo o contraseña son incorrectos");
            flash.addFlashAttribute("errorInicio", errorStart);

            return "redirect:/cuenta/login";
        }

        int rol = loggedUser.getRole().getId();

        switch (rol) {
            case 1:
                session.setAttribute("mostrarRegistro", true);
                session.setAttribute("mostrarClientes", true);
                session.setAttribute("mostrarPlanes", true);
                session.setAttribute("mostrarPromociones", true);
                session.setAttribute("mostrarUsuarios", false);
                break;
            case 2:
                session.setAttribute("mostrarRegistro", true);
                session.setAttribute("mostrarClientes", true);
                session.setAttribute("mostrarPlanes", false);
                session.setAttribute("mostrarPromociones", false);
                session.setAttribute("mostrarUsuarios", false);
                break;
            case 3:
                session.setAttribute("mostrarRegistro", true);
                session.setAttribute("mostrarClientes", true);
                session.setAttribute("mostrarPlanes", false);
                session.setAttribute("mostrarPromociones", false);
                session.setAttribute("mostrarUsuarios", false);
                break;
            case 4:
                session.setAttribute("mostrarRegistro", true);
                session.setAttribute("mostrarClientes", true);
                session.setAttribute("mostrarPlanes", true);
                session.setAttribute("mostrarPromociones", true);
                session.setAttribute("mostrarUsuarios", true);
                break;
            default:
                session.setAttribute("mostrarRegistro", false);
                session.setAttribute("mostrarClientes", false);
                session.setAttribute("mostrarPlanes", false);
                session.setAttribute("mostrarPromociones", false);
                session.setAttribute("mostrarUsuarios", false);
                break;
        }

        session.setAttribute("idUsuario", loggedUser.getId());
        session.setAttribute("nombresCompletos",
                String.format("%s %s", loggedUser.getFirstName(), loggedUser.getPassword()));
        session.setAttribute("cuenta", loggedUser.getFirstName());
        session.setAttribute("rolUsuario", loggedUser.getRole().getDescription());
        session.setAttribute("idRol", rol);

        String alert = Alert
                .sweetAlertSuccess("¡Te damos la bienvenida a Mobilnet, " + loggedUser.getFirstName() + "!");
        flash.addFlashAttribute("alert", alert);

        return "redirect:/";
    }

    @GetMapping("/cerrarSesion")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/cuenta/login";
    }

    @GetMapping("/perfil")
    public String profile(HttpServletRequest request, HttpSession session, Model model, RedirectAttributes flash) {
        model.addAttribute("uri", request.getRequestURI());

        Integer userId = (Integer) session.getAttribute("idUsuario");

        User user = userService.findById(userId);

        UserProfileDTO profileDTO = new UserProfileDTO();
        profileDTO.setEmail(user.getEmail());
        profileDTO.setFirstName(user.getFirstName());
        profileDTO.setLastName(user.getLastName());

        model.addAttribute("perfilDTO", profileDTO);
        model.addAttribute("contrasenaDTO", new UserPasswordDTO());

        return "cuenta/perfil";
    }

    private void uploadAccountForm(Model model, User user, UserProfileDTO profileDTO,
            UserPasswordDTO passwordDTO) {
        if (profileDTO == null) {
            profileDTO = new UserProfileDTO();
            profileDTO.setFirstName(user.getFirstName());
            profileDTO.setLastName(user.getLastName());
            profileDTO.setEmail(user.getEmail());
        }

        profileDTO.setFirstName(user.getFirstName());
        profileDTO.setLastName(user.getLastName());

        if (passwordDTO == null) {
            passwordDTO = new UserPasswordDTO();
        }

        model.addAttribute("perfilDTO", profileDTO);
        model.addAttribute("contrasenaDTO", passwordDTO);
    }

    @PostMapping("/actualizar-perfil")
    public String updateProfile(@Valid @ModelAttribute("perfilDTO") UserProfileDTO dto,
            BindingResult result,
            HttpSession session,
            Model model) {

        Integer userId = (Integer) session.getAttribute("idUsuario");
        User user = userService.findById(userId);

        if (result.hasErrors()) {
            uploadAccountForm(model, user, dto, null);
            return "cuenta/perfil";
        }

        if (user == null) {
            model.addAttribute("alert", Alert.sweetAlertError("Usuario no encontrado"));
            uploadAccountForm(model, user, dto, null);
            return "cuenta/perfil";
        }

        user.setEmail(dto.getEmail());
        ResultResponse res = userService.update(user);

        if (!res.success) {
            model.addAttribute("alert", Alert.sweetAlertError(res.mensaje));
            uploadAccountForm(model, user, dto, null);
            return "cuenta/perfil";
        }

        model.addAttribute("alert", Alert.sweetAlertSuccess("Perfil actualizado correctamente"));
        uploadAccountForm(model, user, dto, null);
        return "cuenta/perfil";
    }

    @PostMapping("/actualizar-contrasena")
    public String updatePassword(@Valid @ModelAttribute("contrasenaDTO") UserPasswordDTO dto,
            BindingResult result,
            HttpSession session,
            Model model) {

        Integer userId = (Integer) session.getAttribute("idUsuario");
        User user = userService.findById(userId);

        if (user == null) {
            model.addAttribute("alert", Alert.sweetAlertError("Usuario no encontrado"));
            uploadAccountForm(model, user, null, dto);
            return "cuenta/perfil";
        }

        if (result.hasErrors()) {
            uploadAccountForm(model, user, null, dto);
            return "cuenta/perfil";
        }

        if (!dto.getConfirmNewPassword().equals(dto.getConfirmNewPassword())) {
            model.addAttribute("alert", Alert.sweetAlertError("La confirmación no coincide con la nueva contraseña"));
            uploadAccountForm(model, user, null, dto);
            return "cuenta/perfil";
        }

        if (!user.getPassword().equals(dto.getCurrentPassword())) {
            model.addAttribute("alert", Alert.sweetAlertError("La contraseña actual es incorrecta"));
            uploadAccountForm(model, user, null, dto);
            return "cuenta/perfil";
        }

        user.setPassword(dto.getNewPassword());
        ResultResponse res = userService.update(user);

        if (!res.success) {
            model.addAttribute("alert", Alert.sweetAlertError(res.mensaje));
            uploadAccountForm(model, user, null, dto);
            return "cuenta/perfil";
        }

        model.addAttribute("alert", Alert.sweetAlertSuccess("Contraseña actualizada correctamente"));
        uploadAccountForm(model, user, null, dto);
        return "cuenta/perfil";
    }
}
