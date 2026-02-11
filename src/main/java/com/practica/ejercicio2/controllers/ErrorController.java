package com.practica.ejercicio2.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/error")
public class ErrorController {

    @GetMapping
    public String irError(Model model){
        log.info("[irError]");

        return "/error/t_error";
    }
}
