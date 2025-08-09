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

import com.mobilnet.dto.UserFilter;
import com.mobilnet.model.User;
import com.mobilnet.service.RoleService;
import com.mobilnet.service.UserService;
import com.mobilnet.utils.Alert;
import com.mobilnet.utils.ResultResponse;

@Controller
@RequestMapping("/usuarios")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@GetMapping({ "", "/" })
	public String list(HttpServletRequest request, Model model) {
		model.addAttribute("uri", request.getRequestURI());

		model.addAttribute("lstRoles", roleService.list());
		model.addAttribute("filtro", new UserFilter());
		model.addAttribute("lstUsuarios", userService.list());

		return "usuarios/index";
	}

	@GetMapping("/filtrado")
	public String filtered(@ModelAttribute UserFilter filter, Model model) {
		model.addAttribute("lstRoles", roleService.list());
		model.addAttribute("filtro", filter);
		model.addAttribute("lstUsuarios", userService.listWithFilters(filter));

		return "usuarios/index";
	}

	@GetMapping("/nuevo")
	public String create(Model model) {
		model.addAttribute("roles", roleService.list());
		model.addAttribute("usuario", new User());
		return "usuarios/nuevo";
	}

	@PostMapping("/registrar")
	public String register(@Validated @ModelAttribute User user, BindingResult bindingResult, Model model,
			RedirectAttributes flash) {
		if (user.getPassword() == null || !user.getPassword()
				.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+=<>?{}\\[\\]-]).{8,}$")) {
			bindingResult.rejectValue("contrasena", "error.usuario",
					"La contraseña debe tener al menos una mayúscula, una minúscula, un número y un símbolo");
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("roles", roleService.list());
			return "usuarios/nuevo";
		}

		ResultResponse response = userService.create(user);

		if (!response.success) {
			model.addAttribute("roles", roleService.list());
			model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
			return "usuarios/nuevo";
		}

		flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
		return "redirect:/usuarios";
	}

	@GetMapping("/edicion/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("roles", roleService.list());
		User user = userService.findById(id);
		model.addAttribute("usuario", user);
		return "usuarios/edicion";
	}

	@PostMapping("/guardar")
	public String save(@Validated @ModelAttribute User user, BindingResult bindingResult, Model model,
			RedirectAttributes flash) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("roles", roleService.list());
			return "usuarios/edicion";
		}

		ResultResponse response = userService.update(user);

		if (!response.success) {
			model.addAttribute("roles", roleService.list());
			model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
			return "usuarios/edicion";
		}

		flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
		return "redirect:/usuarios";
	}

	@PostMapping("/cambiar-estado/{id}")
	public String changeStatus(@PathVariable Integer id, RedirectAttributes flash) {

		ResultResponse response = userService.changeStatus(id);

		flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
		return "redirect:/usuarios";
	}
}
