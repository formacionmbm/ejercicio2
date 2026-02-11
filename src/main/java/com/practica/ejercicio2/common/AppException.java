package com.practica.ejercicio2.common;

public class AppException extends Exception {
    private int code;

    public AppException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
