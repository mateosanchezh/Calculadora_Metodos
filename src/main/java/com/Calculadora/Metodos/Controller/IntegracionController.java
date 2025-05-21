package com.Calculadora.Metodos.Controller;

import com.Calculadora.Metodos.dto.boole.BooleRequest;
import com.Calculadora.Metodos.dto.boole.BooleResponse;
import com.Calculadora.Metodos.dto.simpson.SimpsonRequest;
import com.Calculadora.Metodos.dto.simpson.SimpsonResponse;
import com.Calculadora.Metodos.dto.simpson13.Simpson13Request;
import com.Calculadora.Metodos.dto.simpson13.Simpson13Response;
import com.Calculadora.Metodos.dto.trapecio.TrapezoidalRequest;
import com.Calculadora.Metodos.dto.trapecio.TrapezoidalResponse;
import com.Calculadora.Metodos.service.BooleService;
import com.Calculadora.Metodos.service.Simpson13Service;
import com.Calculadora.Metodos.service.SimpsonService;
import com.Calculadora.Metodos.service.TrapezoidalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/integracion")
@Tag(name = "Integración Numérica", description = "API para cálculo de integrales definidas usando diferentes métodos numéricos")
public class IntegracionController {

    @Autowired
    private TrapezoidalService trapezoidalService;

    @Autowired
    private BooleService booleService;

    @Autowired
    private SimpsonService simpsonService;

    @Autowired
    private Simpson13Service simpson13Service;

    @Operation(
            summary = "Método Trapezoidal",
            description = "Calcula una integral definida usando el método trapezoidal. " +
                    "Este método divide el intervalo en n subintervalos y utiliza trapecios para aproximar el área bajo la curva.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cálculo exitoso",
                            content = @Content(schema = @Schema(implementation = TrapezoidalResponse.class))
                    )
            }
    )
    @PostMapping("/trapezoidal")
    public TrapezoidalResponse metodoTrapezoidal(
            @Parameter(description = "Datos para el cálculo de la integral usando el método trapezoidal",
                    required = true,
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo básico",
                                            summary = "Cálculo de integral x² de 0 a 1",
                                            value = "{ \"funcion\": \"x^2\", \"a\": 0, \"b\": 1, \"n\": 10 }"
                                    )
                            }
                    ))
            @RequestBody TrapezoidalRequest request) {
        return trapezoidalService.calculate(
                request.getFuncion(),
                request.getA(),
                request.getB(),
                request.getN()
        );
    }

    @Operation(
            summary = "Método de Boole",
            description = "Calcula una integral definida usando el método de Boole. " +
                    "Este método es una generalización del método de Simpson de orden superior.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cálculo exitoso",
                            content = @Content(schema = @Schema(implementation = BooleResponse.class))
                    )
            }
    )
    @PostMapping("/boole")
    public BooleResponse metodoBoole(
            @Parameter(description = "Datos para el cálculo de la integral usando el método de Boole",
                    required = true,
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo básico",
                                            summary = "Cálculo de integral x² de 0 a 1",
                                            value = "{ \"funcion\": \"x^2\", \"a\": 0, \"b\": 1 }"
                                    )
                            }
                    ))
            @RequestBody BooleRequest request) {
        return booleService.calculate(
                request.getFuncion(),
                request.getA(),
                request.getB()
        );
    }

    @Operation(
            summary = "Método de Simpson 3/8",
            description = "Calcula una integral definida usando el método de Simpson 3/8. " +
                    "Fórmula: I = (3Δ/8)[f(x₁) + 3f(x₂) + 3f(x₃) + f(x₄)], donde Δ = (b-a)/3. " +
                    "Este método evalúa la función en 4 puntos para aproximar la integral.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cálculo exitoso",
                            content = @Content(schema = @Schema(implementation = SimpsonResponse.class))
                    )
            }
    )
    @PostMapping("/simpson38")
    public SimpsonResponse metodoSimpson38(
            @Parameter(description = "Datos para el cálculo de la integral usando el método de Simpson 3/8",
                    required = true,
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo básico",
                                            summary = "Cálculo de integral x² de 0 a 1",
                                            value = "{ \"funcion\": \"x^2\", \"a\": 0, \"b\": 1 }"
                                    ),
                                    @ExampleObject(
                                            name = "Función trigonométrica",
                                            summary = "Cálculo de integral sen(x) de 0 a π",
                                            value = "{ \"funcion\": \"sin(x)\", \"a\": 0, \"b\": 3.14159 }"
                                    )
                            }
                    ))
            @RequestBody SimpsonRequest request) {
        return simpsonService.calculate(
                request.getFuncion(),
                request.getA(),
                request.getB()
        );
    }

    @Operation(
            summary = "Método de Simpson 1/3",
            description = "Calcula una integral definida usando el método de Simpson 1/3. " +
                    "Fórmula: I = (Δ/3)[f(x₁) + 4f(x₂) + f(x₃)], donde Δ = (b-a)/2. " +
                    "Este método evalúa la función en 3 puntos para aproximar la integral.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cálculo exitoso",
                            content = @Content(schema = @Schema(implementation = Simpson13Response.class))
                    )
            }
    )
    @PostMapping("/simpson13")
    public Simpson13Response metodoSimpson13(
            @Parameter(description = "Datos para el cálculo de la integral usando el método de Simpson 1/3",
                    required = true,
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo básico",
                                            summary = "Cálculo de integral x² de 0 a 1",
                                            value = "{ \"funcion\": \"x^2\", \"a\": 0, \"b\": 1 }"
                                    ),
                                    @ExampleObject(
                                            name = "Función exponencial",
                                            summary = "Cálculo de integral e^x de 0 a 1",
                                            value = "{ \"funcion\": \"exp(x)\", \"a\": 0, \"b\": 1 }"
                                    )
                            }
                    ))
            @RequestBody Simpson13Request request) {
        return simpson13Service.calculate(
                request.getFuncion(),
                request.getA(),
                request.getB()
        );
    }
}