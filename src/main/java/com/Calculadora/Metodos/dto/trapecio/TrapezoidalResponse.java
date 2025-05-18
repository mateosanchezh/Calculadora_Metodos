package com.Calculadora.Metodos.dto.trapecio;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TrapezoidalResponse {
    private double result;
    private String details;
    private List<CoefficientIteration> iterations;
}
