package com.practica.ejercicio2.services.exceptions;

import com.practica.ejercicio2.common.AppException;
import com.practica.ejercicio2.common.CodeError;

public class ServiceException extends AppException {


    public ServiceException() {
        super(CodeError.SERVICE);
    }

    public ServiceException(String message) {
        super(message);
    }


    public int getCode() {
        return CodeError.SERVICE.getCode();
    }
}