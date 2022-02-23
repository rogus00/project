package edu.ib;

import java.util.ArrayList;
import java.util.Arrays;

public class ConsoleHandler implements StepHandler {
    protected ArrayList<Double> xList = new ArrayList<>();
    protected ArrayList<Double> tList = new ArrayList<>();

    @Override
    public void update(double x, double t) {
        xList.add(x);
        tList.add(t);
    }
    public void console(){
        System.out.println(Arrays.toString(xList.toArray()));
        System.out.println(Arrays.toString(tList.toArray()));
    }
    public void printTwoColumns(){
        for(int i = 0; i < xList.size(); i ++){
            System.out.println(tList.get(i) + "\t" + xList.get(i));
        }
    }

    public ArrayList<Double> getxList() {
        return xList;
    }

    public ArrayList<Double> gettList() {
        return tList;
    }
}
