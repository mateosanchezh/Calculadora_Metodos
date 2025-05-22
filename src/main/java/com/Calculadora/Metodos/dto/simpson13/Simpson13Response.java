package com.Calculadora.Metodos.dto.simpson13;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Simpson13Response {
    private String funcion;
    private double a;
    private double b;
    private double resultado;
    private double[] puntosX;
    private double[] valoresF;
    private double delta;
}