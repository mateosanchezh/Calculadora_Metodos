package com.Calculadora.Metodos.exception;

public class EvaluationException extends CalculationException {
    public EvaluationException(String message, Throwable cause) {
        super(message, cause);
    }

    public EvaluationException(String message) {
        super(message);
    }
}