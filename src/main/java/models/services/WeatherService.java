package models.services;

import models.Config;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Lenovo on 19.07.2017.
 */
public class WeatherService {

    private HttpService httpService;
    private double temp;
    private int pressure;
    private int humidity;
    private int cloudy;

    public WeatherService(HttpService httpService) {
        this.httpService = httpService;
    }


    public void makeCall(String city) throws IOException {
        parseJson(httpService.connectAndResponse(Config.APP_URL + "?q=" + city + "&appid=" + Config.APP_ID));
    }

    private void parseJson(String json){
        JSONObject rootObject = new JSONObject(json);
        JSONObject mainObject = rootObject.getJSONObject("main");

        temp = mainObject.getDouble("temp");
        humidity = mainObject.getInt("humidity");
        pressure = mainObject.getInt("pressure");

        JSONObject cloudObject = rootObject.getJSONObject("clouds");
        cloudy = cloudObject.getInt("all");
    }

    public double getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getCloudy() {
        return cloudy;
    }
}
