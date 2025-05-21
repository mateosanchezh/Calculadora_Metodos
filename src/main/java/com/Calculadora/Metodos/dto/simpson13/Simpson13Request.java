package com.Calculadora.Metodos.dto.simpson13;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Simpson13Request {
    private String funcion;
    private double a;
    private double b;
}