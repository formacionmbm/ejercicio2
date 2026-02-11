package com.practica.ejercicio2.services;

public class ServicesUtil {

    public static boolean validUsername(String username){
        if(username == null) return false;
        if(username.trim().isEmpty()) return false;
        return true;
    }
}
