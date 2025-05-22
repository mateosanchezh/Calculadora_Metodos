package com.Calculadora.Metodos.dto.simpson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpsonRequest {
    private String funcion;
    private double a;
    private double b;
}