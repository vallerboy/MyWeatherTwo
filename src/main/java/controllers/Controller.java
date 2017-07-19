package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import models.services.HttpService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    Button buttonShowWeather;

    private HttpService httpService = new HttpService();


    public void initialize(URL location, ResourceBundle resources) {
         buttonShowWeather.setOnMouseClicked(new EventHandler<MouseEvent>() {
             public void handle(MouseEvent event) {
                 try {
                     System.out.println(httpService.connectAndResponse("http://oskarpolak.pl"));
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         });
    }
}
