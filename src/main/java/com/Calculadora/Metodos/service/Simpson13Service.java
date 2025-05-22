package com.Calculadora.Metodos.service;

import com.Calculadora.Metodos.dto.simpson13.Simpson13Response;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

@Service
public class Simpson13Service {

    /**
     * Calcula la integral definida utilizando el método de Simpson 1/3
     * Formula: I = (Δ/3)[f(x₁) + 4f(x₂) + f(x₃)]
     * Donde Δ = (b-a)/2
     */
    public Simpson13Response calculate(String funcion, double a, double b) {
        // Calculamos delta
        double delta = (b - a) / 2.0;

        // Calculamos los puntos donde evaluaremos la función
        double[] puntosX = new double[3];
        puntosX[0] = a;                 // x₁
        puntosX[1] = a + delta;         // x₂ (punto medio)
        puntosX[2] = b;                 // x₃

        // Evaluamos la función en cada punto
        double[] valoresF = new double[3];
        for (int i = 0; i < 3; i++) {
            valoresF[i] = evaluarFuncion(funcion, puntosX[i]);
        }

        // Calculamos la integral según la fórmula Simpson 1/3
        // I = (Δ/3)[f(x₁) + 4f(x₂) + f(x₃)]
        double resultado = (delta / 3.0) * (valoresF[0] + 4 * valoresF[1] + valoresF[2]);

        // Construimos y devolvemos la respuesta
        return Simpson13Response.builder()
                .funcion(funcion)
                .a(a)
                .b(b)
                .resultado(resultado)
                .puntosX(puntosX)
                .valoresF(valoresF)
                .delta(delta)
                .build();
    }

    /**
     * Evalúa la función en un punto específico utilizando exp4j
     */
    private double evaluarFuncion(String funcion, double x) {
        Expression expression = new ExpressionBuilder(funcion)
                .variables("x")
                .build()
                .setVariable("x", x);

        return expression.evaluate();
    }
}