package edu.ib;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public class CalculableHandler implements ODEEquation {
    private String equation;

    public CalculableHandler(String equation) {
        this.equation = equation;
    }

    public String getEquation() {
        return equation;
    }

    @Override
    public double f(double x, double t) throws UnparsableExpressionException, UnknownFunctionException {
        Calculable calc = new ExpressionBuilder(equation)
                .withVariable("t", t)
                .build();
        return calc.calculate();
    }
}
