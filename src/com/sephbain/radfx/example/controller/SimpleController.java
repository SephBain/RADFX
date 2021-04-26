package com.sephbain.radfx.example.controller;

import com.sephbain.radfx.example.model.SimpleRADObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class SimpleController {
    private static  Parent root;
    private static  SimpleController controller;
    @FXML public TableView<SimpleRADObject> table;


    public  static SimpleController getController(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(SimpleController.class.getResource("../view/SimpleForm.fxml"));
            root = loader.load();
            controller = loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return controller;
    }

    public void initialize() {
        for(int i = 0 ; i < 10 ; i++) {
            table.getItems().add(new SimpleRADObject());
        }
    }

}
