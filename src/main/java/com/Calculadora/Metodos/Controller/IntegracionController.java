package com.Calculadora.Metodos.Controller;

import com.Calculadora.Metodos.dto.trapecio.TrapezoidalRequest;
import com.Calculadora.Metodos.dto.trapecio.TrapezoidalResponse;
import com.Calculadora.Metodos.service.TrapezoidalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/integracion")
public class IntegracionController {

    @Autowired
    private TrapezoidalService trapezoidalService;

    @PostMapping("/trapezoidal")
    public TrapezoidalResponse metodoTrapezoidal(@RequestBody TrapezoidalRequest request) {
        return trapezoidalService.calculate(
                request.getFuncion(),
                request.getA(),
                request.getB(),
                request.getN()
        );
    }
}
