package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    Button buttonShowWeather;


    public void initialize(URL location, ResourceBundle resources) {
         buttonShowWeather.setOnMouseClicked(new EventHandler<MouseEvent>() {
             public void handle(MouseEvent event) {
                 System.out.println("Hej! Działam");
             }
         });
    }
}
