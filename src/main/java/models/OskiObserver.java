package models;

import models.services.IWeatherObserver;
import models.services.WeatherService;

/**
 * Created by Lenovo on 19.07.2017.
 */
public class OskiObserver implements IWeatherObserver {

    WeatherService weatherService =  WeatherService.getService();

    public OskiObserver() {
        weatherService.registerObserver(this);
    }

    @Override
    public void onWeatherUpdate(WeatherInfo info) {
        System.out.println("Wooooooo obserwuje pogode!");
    }
}
