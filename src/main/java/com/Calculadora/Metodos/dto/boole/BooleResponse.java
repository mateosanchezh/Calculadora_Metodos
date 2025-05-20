package com.Calculadora.Metodos.dto.boole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BooleResponse {
    private double resultado;
    private List<Double> puntosEvaluados;
    private List<Double> valoresEvaluados;
    private String funcion;
    private double a;
    private double b;
    private double delta;

}


