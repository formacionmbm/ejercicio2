package com.practica.ejercicio2.api;

import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BusquedasFacturasRestController.class)
@Slf4j
class BusquedasFacturasRestControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private Busquedas servicio;

    // =============================
    // GET /api/b/f/{importeMinimo}/{importeMaximo}
    // =============================

    @Test
    void findByImportes_ok() throws Exception {
        log.info("[findByImportes_ok]");
        Factura factura = new Factura();
        factura.setCodigo("FAC-001");
        factura.setImporte(200f);

        when(servicio.busquedaFacturasPorImportes(100f, 300f))
                .thenReturn(List.of(factura));

        mockMvc.perform(get("/api/b/f/100/300"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].codigo").value("FAC-001"))
                .andExpect(jsonPath("$[0].importe").value(200f));
    }

    // =============================
    // GET /api/b/f/{code}
    // =============================

    @Test
    void findByCodigo_ok() throws Exception {
        log.info("[findByCodigo_ok]");
        Factura factura = new Factura();
        factura.setCodigo("FAC-ABC");
        factura.setImporte(150f);

        when(servicio.busquedaFacturaPorCodigo("FAC-ABC"))
                .thenReturn(factura);

        mockMvc.perform(get("/api/b/f/FAC-ABC"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.codigo").value("FAC-ABC"))
                .andExpect(jsonPath("$.importe").value(150f));
    }

    // =============================
    // GET /api/b/t/{tipo}
    // =============================

    @Test
    void findByTipo_ok() throws Exception {
        log.info("[findByTipo_ok]");
        Factura factura = new Factura();
        factura.setTipo(TipoFactura.SERVICIOS);

        when(servicio.busquedaFacturasPorTipo(TipoFactura.SERVICIOS))
                .thenReturn(List.of(factura));

        mockMvc.perform(get("/api/b/t/SERVICIOS"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].tipo").value("SERVICIOS"));
    }
}