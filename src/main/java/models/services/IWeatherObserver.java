package models.services;

import models.WeatherInfo;

/**
 * Created by Lenovo on 19.07.2017.
 */
public interface IWeatherObserver {
    void onWeatherUpdate(WeatherInfo info);
}
