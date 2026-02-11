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



    FacturaRepository repositorio;

    public ServicioBusquedas(FacturaRepository repositorio){
        this.repositorio=repositorio;
    }



    @Override
    public Factura busquedaFacturaPorCodigo(String codigo) throws ServiceException {
        log.info("[busquedaFacturaPorCodigo]");
        log.debug("[codigo:{}]", codigo);
        try {
        // Buscar factura
        Factura factura = repositorio.findByCodigo(codigo)
                .orElseThrow(FacturaNotFoundException::new);

        log.debug("[Factura:{}]", factura);
        return factura;
        } catch (ServiceException se) {
            log.error("Bussiness Error", se);
            throw se;
        } catch (Exception e) {
            log.error("General Error", e);
            throw new ServiceException();
        }
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
    public List<Factura> busquedaFacturasPorImportes(float importeMinimo, float importeMaximo) throws ServiceException {
        log.info("[busquedaFacturasPorImportes]");
        log.debug("[importeMinimo:{}]", importeMinimo);
        log.debug("[importeMaximo:{}]", importeMaximo);


        try{
            if(importeMinimo<0 || importeMaximo<0)
                return List.of();

            return repositorio.findByEntreImportes(importeMinimo,importeMaximo);

        } catch (Exception e) {
            log.error("General Error", e);
            throw new ServiceException();
        }
    }
}
