package com.sephbain.radfx.example;

import com.sephbain.radfx.example.controller.SimpleController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Example extends Application {
    private SimpleController simpleController;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        System.setProperty("javafx.sg.warn", "true");//avoid bug
        simpleController = SimpleController.getController(primaryStage);
    }
}
