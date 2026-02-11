package com.practica.ejercicio2.services.interfaces;


import com.practica.ejercicio2.common.TipoFactura;
import com.practica.ejercicio2.entities.Factura;
import com.practica.ejercicio2.services.exceptions.ServiceException;

import java.util.List;

public interface Busquedas {
    public Factura busquedaFacturaPorCodigo(String codigo) throws ServiceException;
    public List<Factura> busquedaFacturasPorTipo(TipoFactura tipo)throws ServiceException;
    public List<Factura> busquedaFacturasPorImportes(float importeMinimo, float importeMaximo) throws ServiceException;


}
