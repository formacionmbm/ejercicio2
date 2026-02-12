package com.practica.ejercicio2.api;


import com.practica.ejercicio2.common.TipoFactura;
import com.practica.ejercicio2.entities.Factura;
import com.practica.ejercicio2.services.exceptions.ServiceException;
import com.practica.ejercicio2.services.interfaces.Busquedas;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class BusquedasFacturasRestController {

    @Autowired
    Busquedas servicio;

    @GetMapping("/b/f/{importeMinimo}/{importeMaximo}")
    public List<Factura> findByImportes(@PathVariable float importeMinimo,@PathVariable float importeMaximo) throws ServiceException {
        log.info("[findByImportes]");
        List<Factura> facturas = servicio.busquedaFacturasPorImportes(importeMinimo,importeMaximo);

        log.debug("[Facturas:{}",facturas);

        return facturas;
    }

    @GetMapping("/b/f/{code}")
    public Factura findByCodigo(@PathVariable(name="code") String codigo) throws ServiceException{
        log.info("[findByCodigo]");
        log.debug("[codigo:{}]",codigo);

        Factura factura = servicio.busquedaFacturaPorCodigo(codigo);
        log.debug("[Factura:{}",factura);

        return factura;
    }

    @GetMapping("/b/t/{tipo}")
    //faltaba parametro tipo
    public List<Factura> findByTipo(@PathVariable TipoFactura tipo) throws ServiceException{
        log.info("[findByTipo]");
        log.debug("[tipo:{}]",tipo);

        List<Factura> facturas = servicio.busquedaFacturasPorTipo(tipo);
        log.debug("[Facturas:{}",facturas);

        return facturas;
    }
}
