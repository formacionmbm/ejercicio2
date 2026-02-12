package com.practica.ejercicio2.repositories;

import com.practica.ejercicio2.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura,Long> {
    //busca por codigo y devuelve un ptional
    Optional<Factura> findByCodigo(String codigo);
    //Es from Factura porque es la entidad, no la tabla
    @Query("SELECT f FROM Factura f WHERE f.importe BETWEEN :importeMinimo AND :importeMaximo")
    //Devuelve Lista
    List<Factura> findByEntreImportes(float importeMinimo, float importeMaximo);


}
