package com.Calculadora.Metodos.Controller;

import com.Calculadora.Metodos.dto.boole.BooleRequest;
import com.Calculadora.Metodos.dto.boole.BooleResponse;
<<<<<<< Updated upstream
import com.Calculadora.Metodos.dto.trapecio.TrapezoidalRequest;
import com.Calculadora.Metodos.dto.trapecio.TrapezoidalResponse;
import com.Calculadora.Metodos.service.BooleService;
=======
import com.Calculadora.Metodos.dto.simpson.SimpsonRequest;
import com.Calculadora.Metodos.dto.simpson.SimpsonResponse;
import com.Calculadora.Metodos.dto.simpson13.Simpson13Request;
import com.Calculadora.Metodos.dto.simpson13.Simpson13Response;
import com.Calculadora.Metodos.dto.simpsonAbierto.SimpsonAbiertoRequest;
import com.Calculadora.Metodos.dto.simpsonAbierto.SimpsonAbiertoResponse;
import com.Calculadora.Metodos.dto.trapecio.TrapezoidalRequest;
import com.Calculadora.Metodos.dto.trapecio.TrapezoidalResponse;
import com.Calculadora.Metodos.service.BooleService;
import com.Calculadora.Metodos.service.Simpson13Service;
import com.Calculadora.Metodos.service.SimpsonService;
import com.Calculadora.Metodos.service.SimpsonAbiertoService;
>>>>>>> Stashed changes
import com.Calculadora.Metodos.service.TrapezoidalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
@RestController
@RequestMapping("/api/integracion")
public class IntegracionController {

    @Autowired
    private TrapezoidalService trapezoidalService;

    @Autowired
    private BooleService booleService;

<<<<<<< Updated upstream
=======
    @Autowired
    private SimpsonService simpsonService;

    @Autowired
    private Simpson13Service simpson13Service;

    @Autowired
    private SimpsonAbiertoService simpsonAbiertoService;


>>>>>>> Stashed changes
    @PostMapping("/trapezoidal")
    public TrapezoidalResponse metodoTrapezoidal(@RequestBody TrapezoidalRequest request) {
        return trapezoidalService.calculate(
                request.getFuncion(),
                request.getA(),
                request.getB(),
                request.getN()
        );

    }

    @PostMapping("/boole")
    public BooleResponse metodoBoole(@RequestBody BooleRequest request) {
        return booleService.calculate(
                request.getFuncion(),
                request.getA(),
                request.getB()
        );
    }

<<<<<<< Updated upstream

}
=======
    @PostMapping("/simpson38")
    public SimpsonResponse metodoSimpson38(@RequestBody SimpsonRequest request) {
        return simpsonService.calculate(
                request.getFuncion(),
                request.getA(),
                request.getB()
        );
    }

    @PostMapping("/simpson13")
    public Simpson13Response metodoSimpson13(@RequestBody Simpson13Request request) {
        return simpson13Service.calculate(
                request.getFuncion(),
                request.getA(),
                request.getB()
        );
    }


    @PostMapping("/simpson-abierto")
    public SimpsonAbiertoResponse metodoSimpsonAbierto(@RequestBody SimpsonAbiertoRequest request) {
        return simpsonAbiertoService.calculate(
                request.getFuncion(),
                request.getA(),
                request.getB(),
                request.getN()
        );
        }
}
>>>>>>> Stashed changes
