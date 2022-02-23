package edu.ib;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public interface ODEEquation {
    double f(double x, double t) throws UnparsableExpressionException, UnknownFunctionException;
}
