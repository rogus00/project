package edu.ib;

import java.beans.Expression;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;

public class TVDemoController {

    private ODEIntegrator integrator;
    private ObservableList<Points> list = FXCollections.observableArrayList();
    private Grapher grapher;
    private int n = 97;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnEuler;

    @FXML
    private Button btnModifiedEuler;

    @FXML
    private LineChart<Double, Double> chart;

    @FXML
    private TextField equation;

    @FXML
    private TextField h;

    @FXML
    private TextField max;

    @FXML
    private TextField min;

    @FXML
    private TableView<Points> table;

    @FXML
    private TableColumn<Points, Double> x;

    @FXML
    private TextField x0;

    @FXML
    private TableColumn<Points, Double> t;

    @FXML
    private Button reset;

    @FXML
    void btnEulerClicked(ActionEvent event) throws UnparsableExpressionException, UnknownFunctionException, IOException {
        calculate("Euler");
    }

    @FXML
    void btnModifiedEulerClicked(ActionEvent event) throws UnparsableExpressionException, UnknownFunctionException, IOException {
        calculate("EulerMod");
    }

    @FXML
    void btnResetClicked(ActionEvent event) throws UnparsableExpressionException, UnknownFunctionException {
        grapher.clear();
        table.getItems().clear();
        max.clear(); min.clear(); x0.clear(); h.clear(); equation.clear();
        n = 97;
    }

    public void calculate(String method) throws UnparsableExpressionException, UnknownFunctionException, IOException {
        try {
            table.getItems().clear();
            double a = Double.parseDouble(min.getText());
            double b = Double.parseDouble(max.getText());
            double x_0 = Double.parseDouble(x0.getText());

            ConsoleHandler consoleHandler = new ConsoleHandler();
            CalculableHandler ode1 = new CalculableHandler(equation.getText());

            if (Objects.equals(method, "EulerMod"))
                integrator = new ODEIntegrator(a, b, x_0, ode1, new ModifiedEuler(), consoleHandler);
            else integrator = new ODEIntegrator(a, b, x_0, ode1, new Euler(), consoleHandler);
            integrator.integrate(Double.parseDouble(h.getText()));
            list.addAll(Points.getPoints(consoleHandler.tList, consoleHandler.xList));
            grapher.makePlot(consoleHandler.tList, consoleHandler.xList, n);
            n++;
        }catch (Exception e){
            Alert.AlertType type = Alert.AlertType.ERROR;
            Alert alert = new Alert(type, "");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("Wrong parameters, try again!");
            alert.setTitle("ALERT!");
            alert.showAndWait();
        }
    }

    @FXML
    void initialize() {
        assert btnEuler != null : "fx:id=\"btnEuler\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert btnModifiedEuler != null : "fx:id=\"btnModifiedEuler\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert chart != null : "fx:id=\"chart\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert equation != null : "fx:id=\"equation\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert h != null : "fx:id=\"h\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert max != null : "fx:id=\"max\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert min != null : "fx:id=\"min\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert x != null : "fx:id=\"x\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert x0 != null : "fx:id=\"x0\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert t != null : "fx:id=\"t\" was not injected: check your FXML file 'tvdemo.fxml'.";
        assert reset != null : "fx:id=\"reset\" was not injected: check your FXML file 'tvdemo.fxml'.";

        integrator = new ODEIntegrator();

        t.setCellValueFactory(new PropertyValueFactory<Points, Double>("t"));
        x.setCellValueFactory(new PropertyValueFactory<Points, Double>("x"));
        table.setItems(list);

        grapher = new Grapher(chart);

    }

}
