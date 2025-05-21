package com.Calculadora.Metodos.dto.trapecio;

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
public class TrapezoidalResponse {
    private double resultado;
    private String formula;
    private List<CoefficientIteration> iterations;
    private List<Map<String, Double>> tablaValores;
    private List<Map<String, Object>> tablaInteraccion;
    private String funcion;
    private double a;
    private double b;
    private int n;
    private double h;

    public TrapezoidalResponse(double resultado, String formula, List<CoefficientIteration> iterations) {
        this.resultado = resultado;
        this.formula = formula;
        this.iterations = iterations;
    }
}