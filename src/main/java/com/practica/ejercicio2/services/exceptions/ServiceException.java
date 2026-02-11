package com.practica.ejercicio2.services.exceptions;

import com.practica.ejercicio2.common.AppException;

public class ServiceException extends AppException {

    public ServiceException(){
        super("General Error Service Layer");
    }
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
