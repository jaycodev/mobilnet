package com.mobilnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {

    @GetMapping("/acceso-denegado")
    public String accessDenied() {
        return "acceso-denegado";
    }
}