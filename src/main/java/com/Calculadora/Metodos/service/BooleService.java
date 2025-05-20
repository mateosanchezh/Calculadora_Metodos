package com.Calculadora.Metodos.service;

import com.Calculadora.Metodos.dto.boole.BooleResponse;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooleService {

    public BooleResponse calculate(String funcion, double a, double b) {
        if (funcion == null || funcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La función no puede estar vacía");
        }

        if (a >= b) {
            throw new IllegalArgumentException("El límite inferior debe ser menor que el límite superior");
        }

        double delta = (b - a) / 4;

        double x1 = a;
        double x2 = a + delta;
        double x3 = a + 2 * delta;
        double x4 = a + 3 * delta;
        double x5 = b;

        try {
            double fx1 = evaluarFuncion(funcion, x1);
            double fx2 = evaluarFuncion(funcion, x2);
            double fx3 = evaluarFuncion(funcion, x3);
            double fx4 = evaluarFuncion(funcion, x4);
            double fx5 = evaluarFuncion(funcion, x5);

            double resultado = (2 * delta / 45) * (7 * fx1 + 32 * fx2 + 12 * fx3 + 32 * fx4 + 7 * fx5);

            List<Double> puntosEvaluados = List.of(x1, x2, x3, x4, x5);
            List<Double> valoresEvaluados = List.of(fx1, fx2, fx3, fx4, fx5);

            return BooleResponse.builder()
                    .resultado(resultado)
                    .puntosEvaluados(puntosEvaluados)
                    .valoresEvaluados(valoresEvaluados)
                    .funcion(funcion)
                    .a(a)
                    .b(b)
                    .delta(delta)
                    .build();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al evaluar la función: " + e.getMessage());
        }
    }

    private double evaluarFuncion(String funcion, double x) {
        Expression expression = new ExpressionBuilder(funcion)
                .variable("x")
                .build()
                .setVariable("x", x);

        double resultado = expression.evaluate();

        if (Double.isNaN(resultado) || Double.isInfinite(resultado)) {
            throw new IllegalArgumentException("La función produce un valor inválido en x = " + x);
        }

        return resultado;
    }
}
