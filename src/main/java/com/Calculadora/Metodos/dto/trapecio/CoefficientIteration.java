package com.Calculadora.Metodos.dto.trapecio;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CoefficientIteration {
    private int i;
    private double xi;
    private double fxi;
    private int coef;
    private double term;
}
