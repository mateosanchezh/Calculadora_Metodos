package com.Calculadora.Metodos.service;

import com.Calculadora.Metodos.dto.simpson.SimpsonResponse;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import org.springframework.stereotype.Service;

@Service
public class SimpsonService {

    /**
     * Calcula la integral definida utilizando el método de Simpson 3/8
     * Formula: I = (3Δ/8)[f(x₁) + 3f(x₂) + 3f(x₃) + f(x₄)]
     * Donde Δ = (b-a)/3
     */
    public SimpsonResponse calculate(String funcion, double a, double b) {
        try {
            // Calculamos delta
            double delta = (b - a) / 3.0;

            // Calculamos los puntos donde evaluaremos la función
            double[] puntosX = new double[4];
            puntosX[0] = a;                    // x₁
            puntosX[1] = a + delta;            // x₂
            puntosX[2] = a + (2 * delta);      // x₃
            puntosX[3] = b;                    // x₄

            // Evaluamos la función en cada punto
            double[] valoresF = new double[4];
            for (int i = 0; i < 4; i++) {
                valoresF[i] = evaluarFuncion(funcion, puntosX[i]);
            }

            // Calculamos la integral según la fórmula Simpson 3/8
            // I = (3Δ/8)[f(x₁) + 3f(x₂) + 3f(x₃) + f(x₄)]
            double resultado = (3 * delta / 8.0) * (valoresF[0] + 3 * valoresF[1] + 3 * valoresF[2] + valoresF[3]);

            // Construimos y devolvemos la respuesta
            return SimpsonResponse.builder()
                    .funcion(funcion)
                    .a(a)
                    .b(b)
                    .resultado(resultado)
                    .puntosX(puntosX)
                    .valoresF(valoresF)
                    .delta(delta)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Error al evaluar la función: " + e.getMessage(), e);
        }
    }

    /**
     * Evalúa la función en un punto específico utilizando exp4j
     * Incluye soporte para ln() como logaritmo natural
     */
    private double evaluarFuncion(String funcion, double x) {
        // Definir la función ln como logaritmo natural
        Function ln = new Function("ln", 1) {
            @Override
            public double apply(double... args) {
                return Math.log(args[0]); // ln es el logaritmo natural
            }
        };

        // Preprocesar la función para reemplazar algunas funciones comunes
        String funcionProcesada = preprocesarFuncion(funcion);

        Expression expression = new ExpressionBuilder(funcionProcesada)
                .variables("x")
                .function(ln) // Agregar la función ln personalizada
                .build()
                .setVariable("x", x);

        return expression.evaluate();
    }

    /**
     * Preprocesa la función para manejar diferentes notaciones
     */
    private String preprocesarFuncion(String funcion) {
        // Si quieres mantener compatibilidad, puedes hacer reemplazos aquí
        // Por ejemplo, convertir ln a log si es necesario

        // Para este caso, mantenemos ln ya que lo definimos como función personalizada
        return funcion;
    }
}