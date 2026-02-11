package com.practica.ejercicio2.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.practica.ejercicio2.common.TipoFactura;
import com.practica.ejercicio2.entities.Factura;
import com.practica.ejercicio2.services.interfaces.Busquedas;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(BusquedasFacturasController.class)
@Slf4j
class BusquedasFacturasControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private Busquedas servicio;

    // =============================
    // GET /b/f
    // =============================

    @Test
    void busquedaPorCodigo_sinCodigo() throws Exception {
        log.info("[busquedaPorCodigo_sinCodigo]");
        mockMvc.perform(get("/b/f"))
                .andExpect(status().isOk())
                .andExpect(view().name("/busqueda/t_factura"))
                .andExpect(model().attributeExists("tipos"));
    }

    @Test
    void busquedaPorCodigo_conCodigo() throws Exception {
        log.info("[busquedaPorCodigo_conCodigo]");
        Factura factura = new Factura();
        factura.setCodigo("FAC-001");

        when(servicio.busquedaFacturaPorCodigo("FAC-001"))
                .thenReturn(factura);

        mockMvc.perform(get("/b/f").param("codigo", "FAC-001"))
                .andExpect(status().isOk())
                .andExpect(view().name("/busqueda/t_factura"))
                .andExpect(model().attributeExists("tipos"))
                .andExpect(model().attributeExists("factura"))
                .andExpect(model().attribute("factura", factura));
    }

    // =============================
    // GET /b/f/t
    // =============================

    @Test
    void busquedaPorTipo() throws Exception {
        log.info("[busquedaPorTipo]");
        Factura f1 = new Factura();
        f1.setTipo(TipoFactura.SERVICIOS);

        when(servicio.busquedaFacturasPorTipo(TipoFactura.SERVICIOS))
                .thenReturn(List.of(f1));

        mockMvc.perform(get("/b/f/t")
                        .param("tipo", "SERVICIOS"))
                .andExpect(status().isOk())
                .andExpect(view().name("/busqueda/t_factura"))
                .andExpect(model().attributeExists("list"))
                .andExpect(model().attributeExists("tipos"));
    }

    // =============================
    // GET /b/f/i
    // =============================

    @Test
    void busquedaPorImportes_get() throws Exception {
        log.info("[busquedaPorImportes_get]");
        mockMvc.perform(get("/b/f/i"))
                .andExpect(status().isOk())
                .andExpect(view().name("/busqueda/t_factura_importes"));
    }

    // =============================
    // POST /b/f/i
    // =============================

    @Test
    void busquedaPorImportes_post() throws Exception {
        log.info("[busquedaPorImportes_post]");
        Factura factura = new Factura();

        when(servicio.busquedaFacturasPorImportes(anyFloat(), anyFloat()))
                .thenReturn(List.of(factura));

        mockMvc.perform(post("/b/f/i")
                        .param("importeMinimo", "100")
                        .param("importeMaximo", "300"))
                .andExpect(status().isOk())
                .andExpect(view().name("/busqueda/t_factura_importes"))
                .andExpect(model().attributeExists("list"));
    }
}