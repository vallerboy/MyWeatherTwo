package controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import models.OskiObserver;
import models.WeatherInfo;
import models.services.HttpService;
import models.services.IWeatherObserver;
import models.services.WeatherService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, IWeatherObserver {
    @FXML
    Button buttonShowWeather;

    @FXML
    TextField textCity;

    @FXML
    Label textInfo;

    @FXML
    ProgressIndicator progress;

    private WeatherService weatherService = WeatherService.getService();


    public void initialize(URL location, ResourceBundle resources) {
         weatherService.registerObserver(this);

         new OskiObserver();

         buttonShowWeather.setOnMouseClicked(new EventHandler<MouseEvent>() {
             public void handle(MouseEvent event) {
                     weatherService.makeCall(textCity.getText());
                     progress.setVisible(true);
             }
         });
    }

    public void onWeatherUpdate(WeatherInfo info) {
        System.out.println("JEeeeeee uruchomiono mnie bo pogoda sie zaktualizowała!!!!!!");
        textInfo.setText("Temperatura: " + info.getTemp() + "\n"
                + "Wilgotność: " + info.getHumidity() + "\n"
                + "Zachmurzenie: " + info.getCloudy() + "\n"
                + "Ciśnienie: " + info.getPressure()
        );
            progress.setVisible(false);
    }
}
