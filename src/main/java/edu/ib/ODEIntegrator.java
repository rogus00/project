package edu.ib;

import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ODEIntegrator {
    private double tLeft;
    private double tRight;
    private double x0;
    private ODEEquation ode;
    private ODEStep method;
    private StepHandler stepHandler;

    private ArrayList<Double> xValues;
    private ArrayList<Double> tValues;

    ODEIntegrator(){
        xValues= new ArrayList<>();
        tValues= new ArrayList<>();
    }

    public ArrayList<Double> getxValues() {
        return xValues;
    }

    public ArrayList<Double> gettValues() {
        return tValues;
    }

    public ODEIntegrator(double tLeft, double tRight, double x0,
                         ODEEquation ode, ODEStep method, StepHandler stepHandler) {
        this.tLeft = tLeft;
        this.tRight = tRight;
        this.x0 = x0;
        this.ode = ode;
        this.method = method;
        this.stepHandler = stepHandler;
    }

    public void integrate(double h) throws UnparsableExpressionException, UnknownFunctionException {
        double x = x0;
        double t;
        for(t = tLeft; t < tRight; t += h){
            stepHandler.update(x, t);
            x = method.step(x, t, h, ode);
        }
        stepHandler.update(x, t);
    }

    void terminalDisplay(){
        for(int i=0; i < numberOfPoints(); i++){
            System.out.printf("%15.10f \t %15.10f \n",xValues.get(i),tValues.get(i));
        }
    }

    void saveToFile(String fileName
    ) throws IOException {

        StringBuilder sb= new StringBuilder();
        for(int i=0; i<numberOfPoints(); i++){
            double x=xValues.get(i);
            double t=tValues.get(i);
            String sx=String.format("%15.10f",x);
            String st=String.format("%15.10f",t);
            String line= sx+ "\t" + st + "\n";
            sb.append(line);
        }


        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
        writer.append(sb.toString());
        writer.close();

    }

    private void clearData(){
        xValues.clear();
        tValues.clear();
    }

    public int numberOfPoints(){
        return xValues.size();
    }

}
