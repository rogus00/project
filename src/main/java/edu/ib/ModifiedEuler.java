package edu.ib;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public class ModifiedEuler implements ODEStep{
    @Override
    public double step(double x, double t, double h, ODEEquation ode) throws UnparsableExpressionException, UnknownFunctionException {
        return x + ode.f(x + ode.f(x, t) * h / 2.,t + h/2.) * h;
    }
}

