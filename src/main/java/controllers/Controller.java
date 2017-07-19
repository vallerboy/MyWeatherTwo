package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import models.services.HttpService;
import models.services.WeatherService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    Button buttonShowWeather;

    @FXML
    TextField textCity;

    private HttpService httpService = new HttpService();
    private WeatherService weatherService = new WeatherService(httpService);


    public void initialize(URL location, ResourceBundle resources) {
         buttonShowWeather.setOnMouseClicked(new EventHandler<MouseEvent>() {
             public void handle(MouseEvent event) {
                 try {
                     weatherService.makeCall(textCity.getText());
                     System.out.println("Temperatura: " + weatherService.getTemp());
                     System.out.println("Chmurki: " + weatherService.getCloudy());
                     System.out.println("Cisnienie: " + weatherService.getPressure());

                  } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         });
    }
}
