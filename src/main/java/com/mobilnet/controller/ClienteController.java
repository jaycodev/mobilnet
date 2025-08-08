package com.mobilnet.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mobilnet.dto.ClienteFilter;
import com.mobilnet.model.Cliente;
import com.mobilnet.service.ClienteService;
import com.mobilnet.utils.Alert;
import com.mobilnet.utils.ResultadoResponse;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping({ "", "/" })
    public String listado(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("filtro", new ClienteFilter());
        model.addAttribute("lstClientes", clienteService.listarClientes());
        return "clientes/index";
    }

    @GetMapping("/filtrado")
    public String filtrado(@ModelAttribute ClienteFilter filtro, Model model) {
        model.addAttribute("filtro", filtro);
        model.addAttribute("lstClientes", clienteService.listarFiltros(filtro));

        return "clientes/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente());

        return "clientes/nuevo";
    }

    @PostMapping("/registrar")
    public String registrar(@Validated @ModelAttribute Cliente cliente, BindingResult bindingResult, Model model,
            RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            return "clientes/nuevo";
        }

        ResultadoResponse response = clienteService.crearCliente(cliente);

        if (!response.success) {
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "clientes/nuevo";
        }

        flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
        return "redirect:/clientes";
    }

    @GetMapping("/edicion/{id}")
    public String edicion(@PathVariable String id, Model model) {
        Cliente cliente = clienteService.buscarPorId(id);
        model.addAttribute("cliente", cliente);

        return "clientes/edicion";
    }

    @PostMapping("/guardar")
    public String guardar(@Validated @ModelAttribute Cliente cliente, BindingResult bindingResult, Model model,
            RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            return "clientes/edicion";
        }

        ResultadoResponse response = clienteService.modificarCliente(cliente);

        if (!response.success) {
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "clientes/edicion";
        }

        flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
        return "redirect:/clientes";
    }

    @PostMapping("/cambiar-estado/{id}")
    public String cambiarEstado(@PathVariable String id, RedirectAttributes flash) {

        ResultadoResponse response = clienteService.cambiarEstado(id);

        flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
        return "redirect:/clientes";
    }
}
