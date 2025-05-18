package com.Calculadora.Metodos.service;

import com.Calculadora.Metodos.dto.trapecio.CoefficientIteration;
import com.Calculadora.Metodos.dto.trapecio.TrapezoidalResponse;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrapezoidalService {

    public TrapezoidalResponse calculate(String funcion, double a, double b, int n) {
        Expression expression = new ExpressionBuilder(funcion)
                .variable("x")
                .build();

        double h = (b - a) / n;
        double suma = 0;

        List<CoefficientIteration> iterations = new ArrayList<>();

        // f(a)
        double fa = eval(expression, a);
        suma += fa;
        iterations.add(new CoefficientIteration(0, a, fa, 1, fa));

        // Puntos intermedios
        for (int i = 1; i < n; i++) {
            double xi = a + i * h;
            double fxi = eval(expression, xi);
            double term = 2 * fxi;
            suma += term;
            iterations.add(new CoefficientIteration(i, xi, fxi, 2, term));
        }

        // f(b)
        double fb = eval(expression, b);
        suma += fb;
        iterations.add(new CoefficientIteration(n, b, fb, 1, fb));

        double result = (h / 2) * suma;

        StringBuilder formula = new StringBuilder("Método Trapezoidal con ")
                .append(n).append(" segmentos\n")
                .append("Fórmula: (h/2)[f(x₀) + 2f(x₁) + ... + f(xₙ)]\n")
                .append(String.format("h = (%.4f - %.4f) / %d = %.6f", b, a, n, h));

        return new TrapezoidalResponse(result, formula.toString(), iterations);
    }

    private double eval(Expression expr, double x) {
        return expr.setVariable("x", x).evaluate();
    }
}
