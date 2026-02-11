package com.practica.ejercicio2.controllers;


import com.practica.ejercicio2.common.TipoFactura;
import com.practica.ejercicio2.dto.ImportesDTO;
import com.practica.ejercicio2.entities.Factura;
import com.practica.ejercicio2.services.exceptions.ServiceException;
import com.practica.ejercicio2.services.interfaces.Busquedas;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/b/f")
public class BusquedasFacturasController {

    @Autowired
    Busquedas servicio;

    @GetMapping
    public String busquedaPorCodigo(String codigo, Model model) throws ServiceException {
        log.info("[busquedaPorCodigo]");
        log.debug("[codigo:{}]", codigo);

        model.addAttribute("tipos", TipoFactura.values());
        if(codigo==null || codigo.isEmpty()){
            return "/busqueda/t_factura";
        }

        Factura factura = servicio.busquedaFacturaPorCodigo(codigo);
        model.addAttribute("factura", factura);
        log.debug("[Factura:{}", factura);

        return "/busqueda/t_factura";
    }

    @GetMapping("/t")
    public String busquedaPorTipo(TipoFactura tipo, Model model) throws ServiceException {
        log.info("[busquedaPorTipo]");
        log.debug("[tipo:{}]", tipo);
        model.addAttribute("tipos", TipoFactura.values());
        List<Factura> list=servicio.busquedaFacturasPorTipo(tipo);

        model.addAttribute("list", list);
        log.debug("[Facturas List:{}", list);

        return "/busqueda/t_factura";
    }

    @GetMapping("/i")
    public String busquedaPorImportesGet() {
        log.info("[busquedaPorImportes - GET]");
        return "/busqueda/t_factura_importes";
    }
    @PostMapping("/i")
    public String busquedaPorImportes( ImportesDTO importes, Model model) throws ServiceException{
        log.info("[busquedaPorImportes -POST]");
        log.debug("[importes:{}]",importes);

        List<Factura> listado = servicio.busquedaFacturasPorImportes(
                importes.getImporteMinimo(),
                importes.getImporteMaximo());
        log.debug("[Facturas List:{}", listado);
        model.addAttribute("list", listado);
        return "/busqueda/t_factura_importes";
    }


}
