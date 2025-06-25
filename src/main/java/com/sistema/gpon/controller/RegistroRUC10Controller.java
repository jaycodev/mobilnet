package com.sistema.gpon.controller;

import com.sistema.gpon.service.UsuarioService;
import com.sistema.gpon.service.impl.PlanServiceImpl;
import com.sistema.gpon.service.impl.PromocionServiceImpl;
import com.sistema.gpon.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.gpon.service.RegistroRUC10Service;

import java.awt.color.ICC_Profile;

@Controller
@RequestMapping("/formGeneral")
public class RegistroRUC10Controller {

    @Autowired
    private RegistroRUC10Service registroRUC10Service;

    @Autowired
    private UsuarioServiceImpl _UsuarioService;

    @Autowired
    private PromocionServiceImpl _PromocionService;

    @Autowired
    private PlanServiceImpl _PlanService;

    @GetMapping("/formGpon")
    public String formGpon(Model model){
        model.addAttribute("Consultor",_UsuarioService.findByRol_Descripcion("Consultor"));
        model.addAttribute("Supervisor",_UsuarioService.findByRol_Descripcion("Supervisor"));
        model.addAttribute("promocion", _PromocionService.listarPromociones());
        model.addAttribute("plam", _PlanService.listarTodoPlanes());
        return "formGeneral/formGpon";
    }
}
