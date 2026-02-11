package com.practica.ejercicio2.services.exceptions;

import com.practica.ejercicio2.common.AppException;
import com.practica.ejercicio2.common.CodeError;

public class ServiceException extends AppException {


    public ServiceException() {
        super(CodeError.SERVICE.getMessage(),CodeError.SERVICE.getCode());
    }

    public ServiceException(String message) {
        super(message,CodeError.SERVICE.getCode());
    }


    public int getCode() {
        return CodeError.SERVICE.getCode();
    }
}