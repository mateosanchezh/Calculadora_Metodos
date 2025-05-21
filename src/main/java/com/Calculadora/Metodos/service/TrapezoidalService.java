package com.Calculadora.Metodos.service;

import com.Calculadora.Metodos.dto.trapecio.CoefficientIteration;
import com.Calculadora.Metodos.dto.trapecio.TrapezoidalResponse;
import jakarta.validation.constraints.NotNull;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrapezoidalService {

    public TrapezoidalResponse calculate(String funcion, double a, double b, int n) {
        if (funcion == null || funcion.trim().isEmpty()) {
            throw new IllegalArgumentException("La función no puede estar vacía");
        }

        if (a >= b) {
            throw new IllegalArgumentException("El límite inferior debe ser menor que el límite superior");
        }

        if (n <= 0) {
            throw new IllegalArgumentException("El número de segmentos debe ser mayor que cero");
        }

        try {
            Expression expression = new ExpressionBuilder(funcion)
                    .variable("x")
                    .build();

            double h = (b - a) / n;
            double suma = 0;

            List<CoefficientIteration> iterations = new ArrayList<>();
            List<Map<String, Double>> tablaValores = new ArrayList<>();
            List<Map<String, Object>> tablaInteraccion = new ArrayList<>();

            // f(a)
            double fa = eval(expression, a);
            suma += fa;
            iterations.add(new CoefficientIteration(0, a, fa, 1, fa));
            tablaValores.add(crearFilaTablaValores(0, a, fa));
            tablaInteraccion.add(crearFilaTablaInteraccion("f(x₀) [coef = 1]", a, fa, 1.0, fa));

            // Puntos intermedios
            for (int i = 1; i < n; i++) {
                double xi = a + i * h;
                double fxi = eval(expression, xi);
                double term = 2 * fxi;
                suma += term;
                iterations.add(new CoefficientIteration(i, xi, fxi, 2, term));
                tablaValores.add(crearFilaTablaValores(i, xi, fxi));
                tablaInteraccion.add(crearFilaTablaInteraccion("f(x" + i + ") [coef = 2]", xi, fxi, 2.0, term));
            }

            // f(b)
            double fb = eval(expression, b);
            suma += fb;
            iterations.add(new CoefficientIteration(n, b, fb, 1, fb));
            tablaValores.add(crearFilaTablaValores(n, b, fb));
            tablaInteraccion.add(crearFilaTablaInteraccion("f(x" + n + ") [coef = 1]", b, fb, 1.0, fb));

            // Resultado final
            double factorMultiplicacion = h / 2;
            double result = factorMultiplicacion * suma;

            tablaInteraccion.add(crearFilaTablaInteraccion("Suma", null, null, null, suma));
            tablaInteraccion.add(crearFilaTablaInteraccion("Factor (h/2)", null, null, factorMultiplicacion, null));
            tablaInteraccion.add(crearFilaTablaInteraccion("Resultado Final", null, null, null, result));

            StringBuilder formula = new StringBuilder("Método Trapezoidal con ")
                    .append(n).append(" segmentos\n")
                    .append("Fórmula: (h/2)[f(x₀) + 2f(x₁) + ... + f(xₙ)]\n")
                    .append(String.format("h = (%.4f - %.4f) / %d = %.6f", b, a, n, h));

            return TrapezoidalResponse.builder()
                    .resultado(result)
                    .formula(formula.toString())
                    .iterations(iterations)
                    .tablaValores(tablaValores)
                    .tablaInteraccion(tablaInteraccion)
                    .funcion(funcion)
                    .a(a)
                    .b(b)
                    .n(n)
                    .h(h)
                    .build();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al evaluar la función: " + e.getMessage());
        }
    }

    private Map<String, Double> crearFilaTablaValores(int indice, double x, double fx) {
        Map<String, Double> fila = new HashMap<>();
        fila.put("indice", (double) indice);
        fila.put("x", x);
        fila.put("fx", fx);
        return fila;
    }

    private Map<String, Object> crearFilaTablaInteraccion(String descripcion, Double x, Double fx, Double coeficiente, Double resultado) {
        Map<String, Object> fila = new HashMap<>();
        fila.put("descripcion", descripcion);
        if (x != null) fila.put("x", x);
        if (fx != null) fila.put("fx", fx);
        if (coeficiente != null) fila.put("coeficiente", coeficiente);
        if (resultado != null) fila.put("resultado", resultado);
        return fila;
    }

    private double eval(Expression expr, double x) {
        double resultado = expr.setVariable("x", x).evaluate();

        if (Double.isNaN(resultado) || Double.isInfinite(resultado)) {
            throw new IllegalArgumentException("La función produce un valor inválido en x = " + x);
        }

        return resultado;
    }
}