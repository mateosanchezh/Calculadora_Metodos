package com.Calculadora.Metodos.service;

import com.Calculadora.Metodos.dto.simpsonAbierto.SimpsonAbiertoResponse;
import com.Calculadora.Metodos.exception.InvalidParameterException;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

@Service
public class SimpsonAbiertoService {

    public SimpsonAbiertoResponse calculate(String funcion, double a, double b, int n) {
        if (n % 2 != 0) {
            throw new InvalidParameterException("El número de particiones (n) debe ser par.");
        }

        double delta = (b - a) / n;
        double[] puntosX = new double[n + 1];
        double[] valoresF = new double[n + 1];

        // Generar puntos x₀ hasta xₙ
        for (int i = 0; i <= n; i++) {
            puntosX[i] = a + i * delta;
            valoresF[i] = evaluarFuncion(funcion, puntosX[i]);
        }

        double suma = valoresF[0]; // f(x₀)
        for (int i = 1; i < n; i++) {
            if (i % 2 == 0) {
                suma += 2 * valoresF[i];
            } else {
                suma += 4 * valoresF[i];
            }
        }
        suma += valoresF[n]; // f(xₙ)

        double resultado = (delta / 3.0) * suma;

        return SimpsonAbiertoResponse.builder()
                .funcion(funcion)
                .a(a)
                .b(b)
                .n(n)
                .delta(delta)
                .resultado(resultado)
                .puntosX(puntosX)
                .valoresF(valoresF)
                .formulaAplicada("I = (Δ/3) * [f(x₀) + 4f(x₁) + 2f(x₂) + ... + f(xₙ)]")
                .build();
    }

    private double evaluarFuncion(String funcion, double x) {
        Expression expression = new ExpressionBuilder(funcion)
                .variables("x")
                .build()
                .setVariable("x", x);
        return expression.evaluate();
    }
}
