package com.practica.ejercicio2.repositories;

import com.practica.ejercicio2.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura,Long> {

    Optional<Factura> findByCodigo(Long codigo);

    @Query("SELECT * FROM FACTURAS f WHERE f.importe BETWEEN :importeMinimo AND :importeMaximo")
    Factura findByEntreImportes(float importeMinimo, float importeMaximo);


}
