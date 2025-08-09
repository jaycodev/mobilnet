package com.mobilnet.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mobilnet.service.Ruc10RecordService;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    Ruc10RecordService service;

    @GetMapping({ "", "/" })
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("pendientes", service.countByState("Pendiente instalacion"));
        model.addAttribute("anulados", service.countByState("Anulado"));
        model.addAttribute("observados", service.countByState("Observado"));
        model.addAttribute("instalados", service.countByState("Instalado"));

        model.addAttribute("datosPorMes", service.countByMonth());
        model.addAttribute("registrosPorDistrito", service.countByDistrict());

        return "inicio/dashboard";
    }
}
