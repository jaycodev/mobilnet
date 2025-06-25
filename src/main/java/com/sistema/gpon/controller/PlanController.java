package com.sistema.gpon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.gpon.service.PlanService;

@Controller
@RequestMapping("/planes")
public class PlanController {

    @Autowired
    private PlanService planService;
    @GetMapping("/index")
    public String formGpon(Model model){

        return "planes/index";
    }
}
