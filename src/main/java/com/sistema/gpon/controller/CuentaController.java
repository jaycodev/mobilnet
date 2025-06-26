package com.sistema.gpon.controller;

import com.sistema.gpon.model.Usuario;
import com.sistema.gpon.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cuenta")
public class CuentaController {

    @GetMapping("/login")
    public String login() {
        return "cuenta/login";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cuenta/registro";
    }
}
