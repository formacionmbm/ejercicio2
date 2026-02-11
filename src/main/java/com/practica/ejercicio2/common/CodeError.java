package com.practica.ejercicio2.common;

public enum CodeError {SERVICE(700,"ERROR_SERVICE"),FACTURA_NOT_FOUND(701,"ERROR_FACTURA_NOT_FOUND");
    private int code;
    private String message;

    private CodeError(int code,String message){
        this.code=code;
        this.message=message;
    }

    public String getMessage(){
        return this.message;
    }

    public int getCode(){
        return this.code;
    }
}
