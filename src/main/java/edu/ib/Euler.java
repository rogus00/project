package edu.ib;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public class Euler implements ODEStep{
    @Override
    public double step(double x, double t, double h, ODEEquation ode) throws UnparsableExpressionException, UnknownFunctionException {
        return x + ode.f(x, t) * h;
    }
}
