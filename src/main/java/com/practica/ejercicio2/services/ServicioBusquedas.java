package com.practica.ejercicio2.services;


import com.practica.ejercicio2.common.TipoFactura;
import com.practica.ejercicio2.entities.Factura;
import com.practica.ejercicio2.repositories.FacturaRepository;
import com.practica.ejercicio2.services.exceptions.FacturaNotFoundException;
import com.practica.ejercicio2.services.exceptions.ServiceException;
import com.practica.ejercicio2.services.interfaces.Busquedas;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ServicioBusquedas implements Busquedas {


    @Autowired
    FacturaRepository repositorio;



    @Override
    public Factura busquedaFacturaPorCodigo(String codigo) throws ServiceException {
        log.info("[busquedaFacturaPorCodigo]");
        log.debug("[codigo:{}]", codigo);

        // Buscar factura
        Factura factura = repositorio.findAll().stream()
                .filter(f -> f.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(FacturaNotFoundException::new);

        log.debug("[Factura:{}]", factura);
        return factura;
    }

    @Override
    public List<Factura> busquedaFacturasPorTipo(TipoFactura tipo)throws ServiceException {
        log.info("[busquedaFacturaPorTipo]");
        log.debug("[tipo:{}]", tipo);
        try {
            List<Factura> facturas = repositorio.findAll();

            return facturas.stream().filter(f -> f.getTipo()==tipo).toList();

        } catch (Exception e) {
            log.error("General Error", e);
            throw new ServiceException();
        }
    }

    @Override
    public List<Factura> busquedaFacturasPorImportes(float importeMinimo, float importeMaximo) throws FacturaNotFoundException {
        // Validación de importes inválidos
        if (importeMinimo > importeMaximo) {
            throw new FacturaNotFoundException();
        }

        List<Factura> facturas = repositorio.findByImporteBetween(importeMinimo, importeMaximo);

        if (facturas.isEmpty()) {
            throw new FacturaNotFoundException();
        }

        return facturas;
    }



}
