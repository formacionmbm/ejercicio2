package com.practica.ejercicio2.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportesDTO {
    private float importeMinimo;
    private float importeMaximo;

}
