package com.sistema.gpon.controller;

import com.sistema.gpon.dto.UsuarioContrasenaDTO;
import com.sistema.gpon.dto.UsuarioPerfilDTO;
import com.sistema.gpon.model.Usuario;
import com.sistema.gpon.repository.UsuarioRepository;
import com.sistema.gpon.service.UsuarioService;
import com.sistema.gpon.service.impl.UsuarioServiceImpl;
import com.sistema.gpon.utils.Alert;
import com.sistema.gpon.utils.ResultadoResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import javax.swing.text.Document;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cuenta/login";
    }

    @PostMapping("/iniciarSesion")
    public String iniciarSesionPagina(@ModelAttribute Usuario usuario, HttpSession session, Model model, RedirectAttributes flash) {
        Usuario usuarioIngresado = usuarioService.autenticacion(usuario);

        if (usuarioIngresado == null) {
            model.addAttribute("usuario", new Usuario());

            String errorInicio = Alert.sweetAlertError("El correo o contraseña son incorrectos");
            flash.addFlashAttribute("errorInicio", errorInicio);

            return "redirect:/cuenta/login";
        }

        int rol = usuarioIngresado.getRol().getIdRol();

        switch (rol) {
            case 1:
                session.setAttribute("mostrarRegistro", true);
                session.setAttribute("mostrarClientes", false);
                session.setAttribute("mostrarPlanes", true);
                session.setAttribute("mostrarPromociones", true);
                session.setAttribute("mostrarUsuarios", false);
                break;
            case 2:
                session.setAttribute("mostrarRegistro", false);
                session.setAttribute("mostrarClientes", false);
                session.setAttribute("mostrarPlanes", false);
                session.setAttribute("mostrarPromociones", false);
                session.setAttribute("mostrarUsuarios", true);
                break;
            case 3:
                session.setAttribute("mostrarRegistro", true);
                session.setAttribute("mostrarClientes", true);
                session.setAttribute("mostrarPlanes", true);
                session.setAttribute("mostrarPromociones", true);
                session.setAttribute("mostrarUsuarios", true);
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

        // Obtengo informacion del Usuario que se esta logeando
        String nomUsuario = String.format("%s %s", usuarioIngresado.getNombre(), usuarioIngresado.getContrasena());
        session.setAttribute("idUsuario", usuarioIngresado.getIdUsuario());
        session.setAttribute("nombresCompletos", nomUsuario);
        session.setAttribute("cuenta", usuarioIngresado.getNombre());
        session.setAttribute("rolUsuario", usuarioIngresado.getRol().getDescripcion());

        String alertExitoso = Alert.sweetAlertSuccess("¡Te damos la bienvenida a Mobinet, " + usuarioIngresado.getNombre() + "!");
        flash.addFlashAttribute("alertExistoso", alertExitoso);

        return "redirect:/";
    }

    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/cuenta/login";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cuenta/registro";
    }

    @GetMapping("/perfil")
    public String perfil(HttpServletRequest request, HttpSession session, Model model, RedirectAttributes flash) {
        model.addAttribute("uri", request.getRequestURI());

        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

        Usuario usuario = usuarioService.buscarPorId(idUsuario);

        UsuarioPerfilDTO perfilDTO = new UsuarioPerfilDTO();
        perfilDTO.setCorreo(usuario.getCorreo());
        perfilDTO.setNombre(usuario.getNombre());
        perfilDTO.setApellido(usuario.getApellido());

        model.addAttribute("perfilDTO", perfilDTO);
        model.addAttribute("contrasenaDTO", new UsuarioContrasenaDTO());

        return "cuenta/perfil";
    }

    private void cargarFormularioCuenta(Model model, Usuario usuario, UsuarioPerfilDTO perfilDTO, UsuarioContrasenaDTO contrasenaDTO) {
        if (perfilDTO == null) {
            perfilDTO = new UsuarioPerfilDTO();
            perfilDTO.setNombre(usuario.getNombre());
            perfilDTO.setApellido(usuario.getApellido());
            perfilDTO.setCorreo(usuario.getCorreo());
        }

        perfilDTO.setNombre(usuario.getNombre());
        perfilDTO.setApellido(usuario.getApellido());

        if (contrasenaDTO == null) {
            contrasenaDTO = new UsuarioContrasenaDTO();
        }

        model.addAttribute("perfilDTO", perfilDTO);
        model.addAttribute("contrasenaDTO", contrasenaDTO);
    }

    @PostMapping("/actualizar-perfil")
    public String actualizarPerfil(@Valid @ModelAttribute("perfilDTO") UsuarioPerfilDTO dto,
                                   BindingResult result,
                                   HttpSession session,
                                   Model model) {

        Integer idUsuario = (Integer) session.getAttribute("idUsuario");
        Usuario usuario = usuarioService.buscarPorId(idUsuario);

        if (result.hasErrors()) {
            cargarFormularioCuenta(model, usuario, dto, null);
            return "cuenta/perfil";
        }

        if (usuario == null) {
            model.addAttribute("alert", Alert.sweetAlertError("Usuario no encontrado"));
            cargarFormularioCuenta(model, usuario, dto, null);
            return "cuenta/perfil";
        }

        usuario.setCorreo(dto.getCorreo());
        ResultadoResponse res = usuarioService.modificarUsuario(usuario);

        if (!res.success) {
            model.addAttribute("alert", Alert.sweetAlertError(res.mensaje));
            cargarFormularioCuenta(model, usuario, dto, null);
            return "cuenta/perfil";
        }

        model.addAttribute("alert", Alert.sweetAlertSuccess("Perfil actualizado correctamente"));
        cargarFormularioCuenta(model, usuario, dto, null);
        return "cuenta/perfil";
    }

    @PostMapping("/actualizar-contrasena")
    public String actualizarContrasena(@Valid @ModelAttribute("contrasenaDTO") UsuarioContrasenaDTO dto,
                                       BindingResult result,
                                       HttpSession session,
                                       Model model) {

        Integer idUsuario = (Integer) session.getAttribute("idUsuario");
        Usuario usuario = usuarioService.buscarPorId(idUsuario);

        if (usuario == null) {
            model.addAttribute("alert", Alert.sweetAlertError("Usuario no encontrado"));
            cargarFormularioCuenta(model, usuario, null, dto);
            return "cuenta/perfil";
        }

        if (result.hasErrors()) {
            cargarFormularioCuenta(model, usuario, null, dto);
            return "cuenta/perfil";
        }

        if (!dto.getNuevaContrasena().equals(dto.getConfirmarNueva())) {
            model.addAttribute("alert", Alert.sweetAlertError("La confirmación no coincide con la nueva contraseña"));
            cargarFormularioCuenta(model, usuario, null, dto);
            return "cuenta/perfil";
        }

        if (!usuario.getContrasena().equals(dto.getContrasenaActual())) {
            model.addAttribute("alert", Alert.sweetAlertError("La contraseña actual es incorrecta"));
            cargarFormularioCuenta(model, usuario, null, dto);
            return "cuenta/perfil";
        }

        usuario.setContrasena(dto.getNuevaContrasena());
        ResultadoResponse res = usuarioService.modificarUsuario(usuario);

        if (!res.success) {
            model.addAttribute("alert", Alert.sweetAlertError(res.mensaje));
            cargarFormularioCuenta(model, usuario, null, dto);
            return "cuenta/perfil";
        }

        model.addAttribute("alert", Alert.sweetAlertSuccess("Contraseña actualizada correctamente"));
        cargarFormularioCuenta(model, usuario, null, dto);
        return "cuenta/perfil";
    }
}
