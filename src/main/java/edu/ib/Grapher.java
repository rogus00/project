package edu.ib;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.function.Function;

public class Grapher {

    private XYChart graph;
//    private double range;


    public Grapher(XYChart graph) {
        this.graph = graph;
    }


    public XYChart getGraph() {
        return graph;
    }

    public void setGraph(XYChart graph) {
        this.graph = graph;
    }

    public void makePlot(ArrayList<Double> xList, ArrayList<Double> yList, int n){
        XYChart.Series series = new XYChart.Series<>();
        for(int i = 0; i < xList.size(); i ++)
            plotPoint(xList.get(i), yList.get(i), series, n);
        graph.getData().add(series);
    }


    private  void plotPoint(double x, double y, XYChart.Series series, int n){
        series.getData().add(new XYChart.Data(x,y));
        char letter = (char) n;
        series.setName(String.valueOf(letter));
    }

    public void clear(){
        graph.getData().clear();
    }

}
