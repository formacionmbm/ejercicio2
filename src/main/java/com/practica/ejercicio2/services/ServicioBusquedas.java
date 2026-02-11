package com.practica.ejercicio2.services;


import com.practica.ejercicio2.common.TipoFactura;
import com.practica.ejercicio2.entities.Factura;
import com.practica.ejercicio2.repositories.FacturaRepository;
import com.practica.ejercicio2.services.exceptions.FacturaNotFoundException;
import com.practica.ejercicio2.services.exceptions.ServiceException;
import com.practica.ejercicio2.services.interfaces.Busquedas;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class ServicioBusquedas implements Busquedas {

    FacturaRepository repositorio;

    public ServicioBusquedas(FacturaRepository repositorio){
        this.repositorio = repositorio;
    }

    @Override
    public Factura busquedaFacturaPorCodigo(String codigo) throws ServiceException {
        log.info("[busquedaFacturaPorCodigo]");
        log.debug("[codigo:{}]", codigo);
        try {

            return repositorio.findByCodigo(codigo)
                    .orElseThrow(FacturaNotFoundException::new);

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

            return facturas.stream()
                    .filter(f -> f.getTipo()==tipo)
                    .toList();

        } catch (Exception e) {
            log.error("General Error", e);
            throw new ServiceException();
        }
    }

    @Override
    public List<Factura> busquedaFacturasPorImportes(float importeMinimo, float importeMaximo) throws ServiceException {
        log.info("[busquedaFacturasPorImportes]");
        log.debug("[importeMinimo:{}] [importeMaximo:{}]", importeMinimo,importeMaximo);
        try{
            if(importeMinimo<0 || importeMaximo<0)
                return List.of();
            return repositorio.findByEntreImportes(importeMinimo,importeMaximo);
        }catch (Exception e) {
            log.error("General Error", e);
            throw new ServiceException();
        }

    }


}
