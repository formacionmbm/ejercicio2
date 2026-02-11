package com.practica.ejercicio2.repositories;

import com.practica.ejercicio2.entities.Factura;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Slf4j
class FacturaRepositoryTest {

    @Autowired
    private FacturaRepository facturaRepository;

    @Test
    void findByCodigo_ok() {
        log.info("[findByCodigo_ok]");
        // given
        Factura factura = new Factura();
        factura.setCodigo("FAC-001");
        factura.setImporte(150.0f);

        facturaRepository.save(factura);

        // when
        Optional<Factura> resultado = facturaRepository.findByCodigo("FAC-001");

        // then
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getCodigo()).isEqualTo("FAC-001");
        assertThat(resultado.get().getImporte()).isEqualTo(150.0f);
    }

    @Test
    void findByCodigo_ko() {
        log.info("[findByCodigo_ko]");
        // when
        Optional<Factura> resultado = facturaRepository.findByCodigo("NO-EXISTE");

        // then
        assertThat(resultado).isEmpty();
    }

    @Test
    void findByEntreImportes_ok() {
        log.info("[findByEntreImportes_ok]");
        // given
        Factura f1 = new Factura();
        f1.setCodigo("FAC-001");
        f1.setImporte(100.0f);

        Factura f2 = new Factura();
        f2.setCodigo("FAC-002");
        f2.setImporte(200.0f);

        Factura f3 = new Factura();
        f3.setCodigo("FAC-003");
        f3.setImporte(300.0f);

        facturaRepository.saveAll(List.of(f1, f2, f3));

        // when
        List<Factura> resultado = facturaRepository.findByEntreImportes(150.0f, 250.0f);

        // then
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getCodigo()).isEqualTo("FAC-002");
    }

    @Test
    void findByEntreImportes_ko() {
        log.info("[findByEntreImportes_ko]");
        // when
        List<Factura> resultado = facturaRepository.findByEntreImportes(0.0f, 0.0f);

        // then
        assertThat(resultado).isEmpty();
    }
}