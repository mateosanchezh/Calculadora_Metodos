package com.Calculadora.Metodos.dto.simpsonAbierto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpsonAbiertoResponse {
    private String funcion;
    private double a;
    private double b;
    private int n;
    private double delta;
    private double resultado;
    private double[] puntosX;
    private double[] valoresF;
    private String formulaAplicada;
}
