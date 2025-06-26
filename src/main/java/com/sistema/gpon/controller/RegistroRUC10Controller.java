package com.sistema.gpon.controller;

import com.sistema.gpon.service.impl.PlanServiceImpl;
import com.sistema.gpon.service.impl.PromocionServiceImpl;
import com.sistema.gpon.service.impl.UsuarioServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.gpon.service.RegistroRUC10Service;

@Controller
@RequestMapping("/registros")
public class RegistroRUC10Controller {

    @Autowired
    private RegistroRUC10Service registroRUC10Service;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private PromocionServiceImpl promocionService;

    @Autowired
    private PlanServiceImpl planService;

    @GetMapping({"", "/"})
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("Consultor", usuarioService.findByRol_Descripcion("Consultor"));
        model.addAttribute("Supervisor", usuarioService.findByRol_Descripcion("Supervisor"));
        model.addAttribute("promocion", promocionService.listarPromociones());
        model.addAttribute("plam", planService.listarTodoPlanes());

        return "registros/index";
    }
}