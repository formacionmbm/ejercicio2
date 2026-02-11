package com.practica.ejercicio2.entities;


import com.practica.ejercicio2.common.TipoFactura;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String codigo;
    private String concepto;
    private float importe;
    @Enumerated(EnumType.STRING)
    private TipoFactura tipo;
}
