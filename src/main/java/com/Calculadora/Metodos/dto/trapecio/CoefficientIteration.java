package com.Calculadora.Metodos.dto.trapecio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoefficientIteration {
    private int index;
    private double x;
    private double fx;
    private double coefficient;
    private double term;
}