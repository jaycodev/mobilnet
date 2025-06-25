package com.sistema.gpon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.gpon.service.RegistroRUC10Service;

@Controller
@RequestMapping("/mobinet")
public class RegistroRUC10Controller {

    @Autowired
    private RegistroRUC10Service registroRUC10Service;

    @GetMapping("/formGpon")
    public String formGpon(Model model){

        return "formGeneral/formGpon";
    }
}
