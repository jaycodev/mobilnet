package com.sistema.gpon.controller;

import com.sistema.gpon.service.RegistroRUC10Service;
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
    RegistroRUC10Service service;

    @GetMapping({"", "/"})
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("pendientes", service.contarEstado("Pendiente instalacion"));
        model.addAttribute("anulados", service.contarEstado("Anulado"));
        model.addAttribute("observados", service.contarEstado("Observado"));
        model.addAttribute("instalados", service.contarEstado("Instalado"));

        model.addAttribute("datosPorMes", service.contarRegistrosPorMes());
        model.addAttribute("registrosPorDistrito", service.contarRegistrosPorDistrito());

        return "inicio/dashboard";
    }
}
