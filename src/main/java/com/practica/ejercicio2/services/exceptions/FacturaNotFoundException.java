package com.practica.ejercicio2.services.exceptions;

public class FacturaNotFoundException extends ServiceException{
    public FacturaNotFoundException() {
        super("Factura no encontrada");
    }
    public FacturaNotFoundException(String message) {
        super(message);
    }
    public FacturaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
