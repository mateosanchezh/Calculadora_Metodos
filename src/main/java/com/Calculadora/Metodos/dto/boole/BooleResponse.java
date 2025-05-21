package com.Calculadora.Metodos.dto.boole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BooleResponse {
    private double resultado;
    private List<Double> puntosEvaluados;
    private List<Double> valoresEvaluados;
    private List<Map<String, Double>> tablaValores;
    private List<Map<String, Object>> tablaInteraccion;
    private String funcion;
    private double a;
    private double b;
    private double delta;
}