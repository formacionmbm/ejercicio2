package com.practica.ejercicio2.services;

import org.springframework.stereotype.Service;


public class ServicesUtil {

    public boolean validUsername(String username){
        if(username == null) return false;
        if(username.trim().isEmpty()) return false;
        return true;
    }
}
