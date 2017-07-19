package models.services;

import javafx.application.Platform;
import models.Config;
import models.WeatherInfo;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Lenovo on 19.07.2017.
 */
public class WeatherService {

    private static WeatherService ourService = new WeatherService(new HttpService());
    public static  WeatherService getService() {
        return ourService;
    }


    private List<IWeatherObserver> observerList = new ArrayList<>();
    private HttpService httpService;
    private double temp;
    private int pressure;
    private int humidity;
    private int cloudy;

    private ExecutorService executorService;


    private WeatherService(HttpService httpService) {
        this.httpService = httpService;
        executorService = Executors.newFixedThreadPool(1);
    }


    public void makeCall(final String city) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    parseJson(httpService.connectAndResponse(Config.APP_URL + "?q=" + city + "&appid=" + Config.APP_ID));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        executorService.execute(runnable);
    }

    private void parseJson(String json){
        JSONObject rootObject = new JSONObject(json);

        if(rootObject.getInt("cod") != 200){
            System.out.println("Miasto nie istnieje!");
            temp = 0;
            humidity = 0;
            pressure = 0;
            cloudy = 0;
            return;
        }


        JSONObject mainObject = rootObject.getJSONObject("main");
        temp = mainObject.getDouble("temp");
        humidity = mainObject.getInt("humidity");
        pressure = mainObject.getInt("pressure");

        JSONObject cloudObject = rootObject.getJSONObject("clouds");
        cloudy = cloudObject.getInt("all");

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                notifyObservers();
            }
        });
    }

    public void registerObserver(IWeatherObserver observer){
        observerList.add(observer);
    }

    public void notifyObservers(){
        WeatherInfo weatherInfo = new WeatherInfo(temp, pressure, humidity, cloudy);
        for (IWeatherObserver iWeatherObserver : observerList) {
            iWeatherObserver.onWeatherUpdate(weatherInfo);
        }
    }

}
