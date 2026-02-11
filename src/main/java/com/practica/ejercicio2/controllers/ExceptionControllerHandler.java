package com.practica.ejercicio2.controllers;

import com.practica.ejercicio2.common.CodeError;
import com.practica.ejercicio2.common.TipoFactura;
import com.practica.ejercicio2.services.exceptions.FacturaNotFoundException;
import com.practica.ejercicio2.services.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
@Slf4j
public class ExceptionControllerHandler {

    @ExceptionHandler(FacturaNotFoundException.class)
    public String handleEmployeeNotFoundException(
            FacturaNotFoundException ex,
            Model model
    ) {
        log.info("[handleEmployeeNotFoundException]");

        model.addAttribute("error",CodeError.FACTURA_NOT_FOUND.getMessage());
        log.debug("[error {}]",CodeError.FACTURA_NOT_FOUND.getMessage());

        model.addAttribute("tipos", TipoFactura.values());
        return "/busqueda/t_factura";
    }

    @ExceptionHandler(ServiceException.class)
    public String handleServiceException(
            ServiceException ex,
            Model model
    ) {
        log.info("[handleServiceException]");
        model.addAttribute("error",CodeError.SERVICE.getMessage());
        log.debug("[error {}]",CodeError.SERVICE.getMessage());
        return "error/error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(
            Exception ex,
            Model model
    ) {
        log.info("[handleGenericException]");
        model.addAttribute("error","ERROR_500");
        log.debug("[error ERROR_500]");
        return "error/500";
    }
}

