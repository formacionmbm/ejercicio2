package com.practica.ejercicio2.services;


import com.practica.ejercicio2.common.TipoFactura;
import com.practica.ejercicio2.entities.Factura;
import com.practica.ejercicio2.repositories.FacturaRepository;
import com.practica.ejercicio2.services.exceptions.FacturaNotFoundException;
import com.practica.ejercicio2.services.exceptions.ServiceException;
import com.practica.ejercicio2.services.interfaces.Busquedas;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

            return facturas.stream().filter(f -> f.getTipo()==tipo).toList();

        } catch (Exception e) {
            log.error("General Error", e);
            throw new ServiceException();
        }
    }
    // metodo restante que busca por importes
    @Override
    public List<Factura> busquedaFacturasPorImportes(float min, float max) throws ServiceException {
        log.info("[busquedaFacturasPorImportes]");
        log.debug("[min:{}, max:{}]", min, max);
        try {
            // El test exige devolver lista vacía si los importes son negativos
            if (min < 0 || max < 0) return List.of();
            return repositorio.findByEntreImportes(min, max);
        } catch (Exception e) {
            log.error("General Error", e);
            throw new ServiceException();
        }
    }




}
