package com.Calculadora.Metodos.Controller;

import com.Calculadora.Metodos.dto.boole.BooleRequest;
import com.Calculadora.Metodos.dto.boole.BooleResponse;
import com.Calculadora.Metodos.dto.trapecio.TrapezoidalRequest;
import com.Calculadora.Metodos.dto.trapecio.TrapezoidalResponse;
import com.Calculadora.Metodos.service.BooleService;
import com.Calculadora.Metodos.service.TrapezoidalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/integracion")
public class IntegracionController {

    @Autowired
    private TrapezoidalService trapezoidalService;

    @Autowired
    private BooleService booleService;

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


}
