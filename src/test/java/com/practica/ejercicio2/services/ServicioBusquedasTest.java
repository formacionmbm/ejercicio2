package com.practica.ejercicio2.services;

import com.practica.ejercicio2.common.TipoFactura;
import com.practica.ejercicio2.entities.Factura;
import com.practica.ejercicio2.repositories.FacturaRepository;
import com.practica.ejercicio2.services.exceptions.FacturaNotFoundException;
import com.practica.ejercicio2.services.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
class ServicioBusquedasTest {
    @Mock
    private FacturaRepository facturaRepository;

    @InjectMocks
    private ServicioBusquedas servicioBusquedas;

    private Factura factura;

    @BeforeEach
    void setup() {
        log.info("[setup]");
        factura = new Factura();
        factura.setCodigo("FAC-001");
        factura.setImporte(200f);
        factura.setTipo(TipoFactura.SERVICIOS);
    }

    // =============================
    // busquedaFacturaPorCodigo
    // =============================

    @Test
    void busquedaFacturaPorCodigo_ok() throws ServiceException {
        log.info("[busquedaFacturaPorCodigo_ok]");
        when(facturaRepository.findByCodigo("FAC-001"))
                .thenReturn(Optional.of(factura));

        Factura resultado = servicioBusquedas.busquedaFacturaPorCodigo("FAC-001");

        assertThat(resultado).isNotNull();
        assertThat(resultado.getCodigo()).isEqualTo("FAC-001");
    }

    @Test
    void busquedaFacturaPorCodigo_ko() {
        log.info("[busquedaFacturaPorCodigo_ko]");
        when(facturaRepository.findByCodigo("FAC-404"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                servicioBusquedas.busquedaFacturaPorCodigo("FAC-404"))
                .isInstanceOf(FacturaNotFoundException.class);
    }

    @Test
    void busquedaFacturaPorCodigo_errorGeneral() {
        log.info("[busquedaFacturaPorCodigo_errorGeneral]");
        when(facturaRepository.findByCodigo(anyString()))
                .thenThrow(new RuntimeException("DB error"));

        assertThatThrownBy(() ->
                servicioBusquedas.busquedaFacturaPorCodigo("FAC-001"))
                .isInstanceOf(ServiceException.class);
    }

    // =============================
    // busquedaFacturasPorTipo
    // =============================

    @Test
    void busquedaFacturasPorTipo_ok() throws ServiceException {
        log.info("[busquedaFacturasPorTipo_ok]");
        Factura f1 = new Factura();
        f1.setTipo(TipoFactura.SERVICIOS);

        Factura f2 = new Factura();
        f2.setTipo(TipoFactura.CONSUMOS);

        when(facturaRepository.findAll())
                .thenReturn(List.of(f1, f2));

        List<Factura> resultado =
                servicioBusquedas.busquedaFacturasPorTipo(TipoFactura.SERVICIOS);

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getTipo()).isEqualTo(TipoFactura.SERVICIOS);
    }

    @Test
    void busquedaFacturasPorTipo_ko() throws ServiceException {
        log.info("[busquedaFacturasPorTipo_ko]");
        Factura f = new Factura();
        f.setTipo(TipoFactura.SERVICIOS);

        when(facturaRepository.findAll())
                .thenReturn(List.of(f));

        List<Factura> resultado =
                servicioBusquedas.busquedaFacturasPorTipo(TipoFactura.CONSUMOS);

        assertThat(resultado).isEmpty();
    }

    @Test
    void busquedaFacturasPorTipo_error() {
        log.info("[busquedaFacturasPorTipo_error]");
        when(facturaRepository.findAll())
                .thenThrow(new RuntimeException("DB error"));

        assertThatThrownBy(() ->
                servicioBusquedas.busquedaFacturasPorTipo(TipoFactura.CONSUMOS))
                .isInstanceOf(ServiceException.class);
    }

    // =============================
    // busquedaFacturasPorImportes
    // =============================

    @Test
     void busquedaFacturasPorImportes_ok() throws ServiceException {
        log.info("[busquedaFacturasPorImportes_ok]");
        when(facturaRepository.findByEntreImportes(100f, 300f))
                .thenReturn(List.of(factura));

        List<Factura> resultado =
                servicioBusquedas.busquedaFacturasPorImportes(100f, 300f);

        assertThat(resultado).hasSize(1);
    }

    @Test
    void busquedaFacturasPorImportes_importesNegativos() throws ServiceException {
        log.info("[busquedaFacturasPorImportes_importesNegativos]");
        List<Factura> resultado =
                servicioBusquedas.busquedaFacturasPorImportes(-10f, 100f);

        assertThat(resultado).isEmpty();
        verifyNoInteractions(facturaRepository);
    }

    @Test
     void busquedaFacturasPorImportes_error() {
        log.info("[busquedaFacturasPorImportes_error]");
        when(facturaRepository.findByEntreImportes(anyFloat(), anyFloat()))
                .thenThrow(new RuntimeException("DB error"));

        assertThatThrownBy(() ->
                servicioBusquedas.busquedaFacturasPorImportes(100f, 200f))
                .isInstanceOf(ServiceException.class);
    }
}