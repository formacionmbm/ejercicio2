package com.practica.ejercicio2.services.interfaces;


import com.practica.ejercicio2.common.TipoFactura;
import com.practica.ejercicio2.entities.Factura;
import com.practica.ejercicio2.services.exceptions.ServiceException;

import java.util.List;

public interface Busquedas {
    public Factura busquedaFacturaPorCodigo(String codigo) throws ServiceException;

    Factura busquedaFacturaPorCodigo(Long codigo) throws ServiceException;

    List<Factura> busquedaFacturasPorTipo(TipoFactura tipo)throws ServiceException;
    List<Factura> busquedaFacturasPorImportes(float importeMinimo, float importeMaximo) throws ServiceException;


}
