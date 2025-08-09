package com.mobilnet.controller;

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

import com.mobilnet.dto.PromotionFilter;
import com.mobilnet.model.Promotion;
import com.mobilnet.service.PromotionService;
import com.mobilnet.utils.Alert;
import com.mobilnet.utils.ResultResponse;

@Controller
@RequestMapping("/promociones")
public class PromotionController {

    @Autowired
    private PromotionService service;

    @GetMapping({ "", "/" })
    public String list(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("filtro", new PromotionFilter());
        model.addAttribute("lstPromociones", service.list());
        return "promociones/index";
    }

    @GetMapping("/filtrado")
    public String filtered(@ModelAttribute PromotionFilter filter, Model model) {
        model.addAttribute("filtro", filter);
        model.addAttribute("lstPromociones", service.listWithFilters(filter));

        return "promociones/index";
    }

    @GetMapping("/nuevo")
    public String create(Model model) {
        model.addAttribute("promocion", new Promotion());
        return "promociones/nuevo";
    }

    @PostMapping("/registrar")
    public String register(@Validated @ModelAttribute Promotion promotion, BindingResult bindingResult, Model model,
            RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            return "promociones/nuevo";
        }

        ResultResponse response = service.create(promotion);

        if (!response.success) {
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "inventarios/nuevo";
        }

        flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
        return "redirect:/promociones";
    }

    @GetMapping("/edicion/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Promotion promotion = service.findById(id);
        model.addAttribute("promocion", promotion);
        return "promociones/edicion";
    }

    @PostMapping("/guardar")
    public String save(@Validated @ModelAttribute Promotion promotion, BindingResult bindingResult, Model model,
            RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            return "promociones/edicion";
        }

        ResultResponse response = service.update(promotion);

        if (!response.success) {
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "promociones/edicion";
        }

        flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
        return "redirect:/promociones";
    }

    @PostMapping("/cambiar-estado/{id}")
    public String changeStatus(@PathVariable Integer id, RedirectAttributes flash) {
        ResultResponse response = service.changeStatus(id);

        flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
        return "redirect:/promociones";
    }
}
