package com.sistema.gpon.controller;

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


import com.sistema.gpon.model.Promocion;
import com.sistema.gpon.service.PromocionService;
import com.sistema.gpon.utils.Alert;
import com.sistema.gpon.utils.ResultadoResponse;

@Controller
@RequestMapping("/promociones")
public class PromocionController {

    @Autowired
    private PromocionService promocionService;
    
    @GetMapping("/index")
	public String listado(Model model) {
		List<Promocion> lstPromocion = promocionService.listarPromociones();
		model.addAttribute("lstPromocion", lstPromocion);
		return "promociones/index";
	}
    
    @GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("promocion", new Promocion());
		return "promociones/nuevo";
	}
    
    @PostMapping("/registrar")
	public String registrar(@Validated @ModelAttribute Promocion Promocion, BindingResult bindingResults, Model model, RedirectAttributes flash) {
		
    	ResultadoResponse response = promocionService.crearPromocion(Promocion);

		if (!response.success) {

			model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
			return "inventarios/nuevo";
		}
		flash.addFlashAttribute("alert", Alert.sweetToast(response.mensaje, "success", 5000));
		return "redirect:/promociones/index";
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
    	
    	ResultadoResponse response = promocionService.actualizarPromocion(promocion);

		if (!response.success) {
			model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
			return "inventarios/edicion";
		}

		String toast = Alert.sweetToast(response.mensaje, "success", 5000);
		flash.addFlashAttribute("alert", toast);
		return "redirect:/promociones/index";
	}
}
