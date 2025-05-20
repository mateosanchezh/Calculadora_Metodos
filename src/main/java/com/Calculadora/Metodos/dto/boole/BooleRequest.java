package com.Calculadora.Metodos.dto.boole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooleRequest {
    private String funcion;
    private double a; //Limite inferior
    private double b; // Limite superior

}


