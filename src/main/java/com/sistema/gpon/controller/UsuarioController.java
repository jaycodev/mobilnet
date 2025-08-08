package com.sistema.gpon.controller;

import com.sistema.gpon.dto.UsuarioFilter;
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

import com.sistema.gpon.model.Usuario;
import com.sistema.gpon.service.RolService;
import com.sistema.gpon.service.UsuarioService;
import com.sistema.gpon.utils.Alert;
import com.sistema.gpon.utils.ResultadoResponse;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RolService rolesService;

	@GetMapping({ "", "/" })
	public String listado(HttpServletRequest request, Model model) {
		model.addAttribute("uri", request.getRequestURI());

		model.addAttribute("lstRoles", rolesService.listarRoles());
		model.addAttribute("filtro", new UsuarioFilter());
		model.addAttribute("lstUsuarios", usuarioService.listarUsuarios());

		return "usuarios/index";
	}

	@GetMapping("/filtrado")
	public String filtrado(@ModelAttribute UsuarioFilter filtro, Model model) {
		model.addAttribute("lstRoles", rolesService.listarRoles());
		model.addAttribute("filtro", filtro);
		model.addAttribute("lstUsuarios", usuarioService.listarFiltros(filtro));

		return "usuarios/index";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("roles", rolesService.listarRoles());
		model.addAttribute("usuario", new Usuario());
		return "usuarios/nuevo";
	}

	@PostMapping("/registrar")
	public String registrar(@Validated @ModelAttribute Usuario usuario, BindingResult result, Model model,
			RedirectAttributes flash) {
		if (usuario.getContrasena() == null || !usuario.getContrasena()
				.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=<>?{}\\[\\]-]).{8,}$")) {
			result.rejectValue("contrasena", "error.usuario",
					"La contraseña debe tener al menos una mayúscula, una minúscula, un número y un símbolo");
		}

		if (result.hasErrors()) {
			model.addAttribute("roles", rolesService.listarRoles());
			return "usuarios/nuevo";
		}

		ResultadoResponse response = usuarioService.crearUsuario(usuario);

		if (!response.success) {
			model.addAttribute("roles", rolesService.listarRoles());
			model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
			return "usuarios/nuevo";
		}

		flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
		return "redirect:/usuarios";
	}

	@GetMapping("/edicion/{id}")
	public String edicion(@PathVariable Integer id, Model model) {
		model.addAttribute("roles", rolesService.listarRoles());
		Usuario usuario = usuarioService.buscarPorId(id);
		model.addAttribute("usuario", usuario);
		return "usuarios/edicion";
	}

	@PostMapping("/guardar")
	public String guardar(@Validated @ModelAttribute Usuario usuario, BindingResult bindingResult, Model model,
			RedirectAttributes flash) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("roles", rolesService.listarRoles());
			return "usuarios/edicion";
		}

		ResultadoResponse response = usuarioService.modificarUsuario(usuario);

		if (!response.success) {
			model.addAttribute("roles", rolesService.listarRoles());
			model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
			return "usuarios/edicion";
		}

		flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
		return "redirect:/usuarios";
	}

	@PostMapping("/cambiar-estado/{id}")
	public String cambiarEstado(@PathVariable Integer id, RedirectAttributes flash) {

		ResultadoResponse response = usuarioService.cambiarEstado(id);

		flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
		return "redirect:/usuarios";
	}
}
