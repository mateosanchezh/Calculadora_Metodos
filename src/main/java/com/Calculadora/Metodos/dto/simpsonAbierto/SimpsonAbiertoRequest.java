package com.Calculadora.Metodos.dto.simpsonAbierto;

import lombok.Data;

@Data
public class SimpsonAbiertoRequest {
    private String funcion;
    private double a;
    private double b;
    private int n; // Debe ser par
}
