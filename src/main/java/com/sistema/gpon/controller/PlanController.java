package com.sistema.gpon.controller;

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
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("lstPlanes", planService.listarPlanes());
        return "planes/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("plan", new Plan());
        return "planes/nuevo";
    }

    @PostMapping("/registrar")
    public String registrarPlan(@Validated @ModelAttribute Plan plan, BindingResult bindingResults, Model model,
                                RedirectAttributes flash) {
        ResultadoResponse response = planService.crearPlan(plan);

        if (!response.success) {
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "planes/nuevo";
        }

        flash.addFlashAttribute("alert", Alert.sweetToast(response.mensaje, "success", 5000));

        return "redirect:/promociones";
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
        ResultadoResponse response = planService.actualizarPlan(plan);

        if (!response.success) {
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "planes/edicion";
        }

        flash.addFlashAttribute("alert", Alert.sweetToast(response.mensaje, "success", 5000));

        return "redirect:/planes";
    }

    @PostMapping("/cambiar-estado/{id}")
    public String cambiarEstado(@PathVariable Integer id, RedirectAttributes flash) {

        ResultadoResponse response = planService.cambiarEstado(id);

        String toast = Alert.sweetToast(response.mensaje, "success", 5000);
        flash.addFlashAttribute("alert", toast);
        return "redirect:/planes";
    }
}
