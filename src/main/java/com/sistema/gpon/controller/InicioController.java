package com.sistema.gpon.controller;

import com.sistema.gpon.service.impl.RegistroRUC10ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class InicioController {

    @Autowired
    RegistroRUC10ServiceImpl _Service;

    @GetMapping({"", "/"})
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("pendiente",_Service.countEstado("Pendiente instalacion"));
        model.addAttribute("anulado",_Service.countEstado("Anulado"));
        model.addAttribute("observado",_Service.countEstado("Observado"));
        model.addAttribute("instalado",_Service.countEstado("Instalado"));
        return "inicio/dashboard";
    }
}