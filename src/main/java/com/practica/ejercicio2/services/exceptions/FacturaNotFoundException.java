package com.practica.ejercicio2.services.exceptions;

public class FacturaNotFoundException extends ServiceException{
    public FacturaNotFoundException() {
        super("Factura no encontrada");
    }
}
