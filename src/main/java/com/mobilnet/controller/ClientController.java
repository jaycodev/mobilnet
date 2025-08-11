package com.mobilnet.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mobilnet.dto.ClientFilter;
import com.mobilnet.model.Client;
import com.mobilnet.service.ClientService;
import com.mobilnet.utils.Alert;
import com.mobilnet.utils.ResultResponse;

@Controller
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping({ "", "/" })
    public String list(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("filtro", new ClientFilter());
        model.addAttribute("lstClientes", service.list());
        return "clientes/index";
    }

    @GetMapping("/filtrado")
    public String filtered(@ModelAttribute ClientFilter filter, Model model) {
        model.addAttribute("filtro", filter);
        model.addAttribute("lstClientes", service.listWithFilters(filter));

        return "clientes/index";
    }

    @GetMapping("/nuevo")
    public String create(Model model) {
        model.addAttribute("client", new Client());

        return "clientes/nuevo";
    }

    @PostMapping("/registrar")
    public String register(@Validated @ModelAttribute Client client, BindingResult bindingResult, Model model,
            RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            return "clientes/nuevo";
        }

        ResultResponse response = service.create(client);

        if (!response.success) {
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "clientes/nuevo";
        }

        flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
        return "redirect:/clientes";
    }

    @GetMapping("/edicion/{id}")
    public String edit(@PathVariable String id, Model model) {
        Client client = service.findById(id);
        model.addAttribute("client", client);

        return "clientes/edicion";
    }

    @PostMapping("/guardar")
    public String save(@Validated @ModelAttribute Client client, BindingResult bindingResult, Model model,
            RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            return "clientes/edicion";
        }

        ResultResponse response = service.update(client);

        if (!response.success) {
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "clientes/edicion";
        }

        flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
        return "redirect:/clientes";
    }

    @PostMapping("/cambiar-estado/{id}")
    public String changeStatus(@PathVariable String id, RedirectAttributes flash) {

        ResultResponse response = service.changeStatus(id);

        flash.addFlashAttribute("alert", Alert.sweetAlertSuccess(response.mensaje));
        return "redirect:/clientes";
    }
}
