package com.sistema.gpon.controller;

import com.sistema.gpon.dto.PromocionFilter;
import jakarta.servlet.http.HttpServletRequest;
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

import com.sistema.gpon.model.Promocion;
import com.sistema.gpon.service.PromocionService;
import com.sistema.gpon.utils.Alert;
import com.sistema.gpon.utils.ResultadoResponse;

@Controller
@RequestMapping("/promociones")
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @GetMapping({ "", "/" })
    public String listado(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("filtro", new PromocionFilter());
        model.addAttribute("lstPromociones", promocionService.listarPromociones());
        return "promociones/index";
    }

    @GetMapping("/filtrado")
    public String filtrado(@ModelAttribute PromocionFilter filtro, Model model) {
        model.addAttribute("filtro", filtro);
        model.addAttribute("lstPromociones", promocionService.listarFiltros(filtro));

        return "promociones/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("promocion", new Promocion());
        return "promociones/nuevo";
    }

    @PostMapping("/registrar")
    public String registrar(@Validated @ModelAttribute Promocion Promocion, BindingResult bindingResult, Model model,
            RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            return "promociones/nuevo";
        }

        ResultadoResponse response = promocionService.crearPromocion(Promocion);

        if (!response.success) {
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "inventarios/nuevo";
        }

        flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
        return "redirect:/promociones";
    }

    @GetMapping("/edicion/{id}")
    public String edicion(@PathVariable Integer id, Model model) {
        Promocion promocion = promocionService.buscarPorId(id);
        model.addAttribute("promocion", promocion);
        return "promociones/edicion";
    }

    @PostMapping("/guardar")
    public String guardar(@Validated @ModelAttribute Promocion promocion, BindingResult bindingResult, Model model,
            RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            return "promociones/edicion";
        }

        ResultadoResponse response = promocionService.actualizarPromocion(promocion);

        if (!response.success) {
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "promociones/edicion";
        }

        flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
        return "redirect:/promociones";
    }

    @PostMapping("/cambiar-estado/{id}")
    public String cambiarEstado(@PathVariable Integer id, RedirectAttributes flash) {

        ResultadoResponse response = promocionService.cambiarEstado(id);

        flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
        return "redirect:/promociones";
    }
}
