package com.practica.ejercicio2.repositories;

import com.practica.ejercicio2.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura,Long> {

    Optional<Factura> findByCodigo(String codigo);

    // dentro de FacturaRepository
    default List<Factura> findByEntreImportes(float importeMinimo, float importeMaximo) {
        return findByImporteBetween(importeMinimo, importeMaximo);
    }

    List <Factura> findByImporteBetween(float importeMinimo, float importeMaximo);


}
