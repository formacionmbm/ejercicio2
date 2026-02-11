package com.practica.ejercicio2.repositories;

import com.practica.ejercicio2.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura,String> {

    Optional<Factura> findByCodigo(String codigo);

    @Query("SELECT f FROM Factura f WHERE f.importe BETWEEN :importeMinimo AND :importeMaximo")
    List<Factura> findByEntreImportes(@Param("importeMinimo") float importeMinimo,
                                      @Param("importeMaximo") float importeMaximo);


}
