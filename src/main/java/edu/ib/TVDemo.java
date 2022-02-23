package edu.ib;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TVDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root= FXMLLoader.load(getClass().getResource("/fxml/tvdemo.fxml"));
        Scene scene= new Scene(root,800,600);
        scene.getStylesheets().add("style1.css");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
