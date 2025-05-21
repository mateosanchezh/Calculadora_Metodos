package com.Calculadora.Metodos.service;

import com.Calculadora.Metodos.dto.boole.BooleResponse;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            // Crear la tabla de valores
            List<Map<String, Double>> tablaValores = new ArrayList<>();
            tablaValores.add(crearFilaTablaValores("x1", x1, fx1));
            tablaValores.add(crearFilaTablaValores("x2", x2, fx2));
            tablaValores.add(crearFilaTablaValores("x3", x3, fx3));
            tablaValores.add(crearFilaTablaValores("x4", x4, fx4));
            tablaValores.add(crearFilaTablaValores("x5", x5, fx5));

            // Calcular los componentes de la fórmula de Boole
            double componente1 = 7 * fx1;
            double componente2 = 32 * fx2;
            double componente3 = 12 * fx3;
            double componente4 = 32 * fx4;
            double componente5 = 7 * fx5;
            double sumaComponentes = componente1 + componente2 + componente3 + componente4 + componente5;
            double factorMultiplicacion = 2 * delta / 45;
            double resultado = factorMultiplicacion * sumaComponentes;

            // Crear la tabla de interacción
            List<Map<String, Object>> tablaInteraccion = new ArrayList<>();
            tablaInteraccion.add(crearFilaTablaInteraccion("7 * f(x1)", componente1));
            tablaInteraccion.add(crearFilaTablaInteraccion("32 * f(x2)", componente2));
            tablaInteraccion.add(crearFilaTablaInteraccion("12 * f(x3)", componente3));
            tablaInteraccion.add(crearFilaTablaInteraccion("32 * f(x4)", componente4));
            tablaInteraccion.add(crearFilaTablaInteraccion("7 * f(x5)", componente5));
            tablaInteraccion.add(crearFilaTablaInteraccion("Suma", sumaComponentes));
            tablaInteraccion.add(crearFilaTablaInteraccion("Factor (2h/45)", factorMultiplicacion));
            tablaInteraccion.add(crearFilaTablaInteraccion("Resultado", resultado));

            List<Double> puntosEvaluados = List.of(x1, x2, x3, x4, x5);
            List<Double> valoresEvaluados = List.of(fx1, fx2, fx3, fx4, fx5);

            return BooleResponse.builder()
                    .resultado(resultado)
                    .puntosEvaluados(puntosEvaluados)
                    .valoresEvaluados(valoresEvaluados)
                    .tablaValores(tablaValores)
                    .tablaInteraccion(tablaInteraccion)
                    .funcion(funcion)
                    .a(a)
                    .b(b)
                    .delta(delta)
                    .build();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al evaluar la función: " + e.getMessage());
        }
    }

    private Map<String, Double> crearFilaTablaValores(String punto, double x, double fx) {
        Map<String, Double> fila = new HashMap<>();
        fila.put("punto", Double.parseDouble(punto.substring(1)));
        fila.put("x", x);
        fila.put("fx", fx);
        return fila;
    }

    private Map<String, Object> crearFilaTablaInteraccion(String descripcion, double valor) {
        Map<String, Object> fila = new HashMap<>();
        fila.put("descripcion", descripcion);
        fila.put("valor", valor);
        return fila;
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