package com.practica.ejercicio2.repositories;

import com.practica.ejercicio2.common.TipoFactura;
import com.practica.ejercicio2.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura,Long> {

    Optional<Factura> findByCodigo(String codigo);
    @Query(value="SELECT f FROM FACTURA f WHERE f.importe BETWEEN :importeMinimo AND :importeMaximo", nativeQuery=true)
    List<Factura> findByEntreImportes(float importeMinimo, float importeMaximo);


}
