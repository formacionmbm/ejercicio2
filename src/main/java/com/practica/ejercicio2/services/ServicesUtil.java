package com.practica.ejercicio2.services;

public class ServicesUtil {

    public static boolean validUsername(String username){
        if(username == null) return false;
        if(username.trim().isEmpty()) return false;
        return true;
    }

    public static boolean validImporteNoNegativo(float importe){
        if(importe < 0) return false;
        return true;
    }

    public static boolean validImporteNoNulo(Float importe){
        if(importe == null) return false;
        return true;
    }
}
