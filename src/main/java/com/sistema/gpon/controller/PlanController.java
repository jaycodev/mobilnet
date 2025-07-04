package com.sistema.gpon.controller;

import com.sistema.gpon.dto.ClienteFilter;
import com.sistema.gpon.dto.PlanFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.gpon.service.PlanService;
import com.sistema.gpon.service.impl.ClienteServiceImpl;
import com.sistema.gpon.utils.Alert;
import com.sistema.gpon.utils.ResultadoResponse;
import com.sistema.gpon.model.*;

@Controller
@RequestMapping("/planes")
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping({"", "/"})
    public String listado(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("filtro", new PlanFilter());
        model.addAttribute("lstPlanes", planService.listarPlanes());
        return "planes/index";
    }

    @GetMapping("/filtrado")
    public String filtrado(@ModelAttribute PlanFilter filtro, Model model) {
    	
        model.addAttribute("filtro", filtro);
        model.addAttribute("lstPlanes", planService.listarFiltros(filtro));

        return "planes/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("plan", new Plan());
        return "planes/nuevo";
    }

    @PostMapping("/registrar")
    public String registrarPlan(@Validated @ModelAttribute Plan plan, BindingResult bindingResult, Model model,
                                RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            return "planes/nuevo";
        }

        ResultadoResponse response = planService.crearPlan(plan);

        if (!response.success) {
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "planes/nuevo";
        }

        flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
        return "redirect:/planes";
    }

    @GetMapping("/edicion/{id}")
    public String edicion(@PathVariable Integer id, Model model) {
        Plan planObtenido = planService.buscarPorId(id);
        model.addAttribute("plan", planObtenido);
        return "planes/edicion";
    }

    @PostMapping("/guardar")
    public String guardar(@Validated @ModelAttribute Plan plan, BindingResult bindingResult, Model model,
                          RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            return "planes/edicion";
        }

        ResultadoResponse response = planService.actualizarPlan(plan);

        if (!response.success) {
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "planes/edicion";
        }

        flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
        return "redirect:/planes";
    }

    @PostMapping("/cambiar-estado/{id}")
    public String cambiarEstado(@PathVariable Integer id, RedirectAttributes flash) {

        ResultadoResponse response = planService.cambiarEstado(id);

        flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
        return "redirect:/planes";
    }
}
