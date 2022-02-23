package edu.ib;

import java.util.ArrayList;

public class Points {
    private  double t;
    private  double x;

    public Points(double t, double x) {
        this.t = t;
        this.x = x;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public  static ArrayList<Points> getPoints(ArrayList<Double> t, ArrayList<Double> x){
        ArrayList<Points> points= new ArrayList<>();
        for(int i=0; i < t.size(); i++)
            points.add(new Points(t.get(i),x.get(i)));
        return points;
    }
}
