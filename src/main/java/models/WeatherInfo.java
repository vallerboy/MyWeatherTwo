package models;

/**
 * Created by Lenovo on 19.07.2017.
 */
public class WeatherInfo {
    private double temp;
    private int pressure;
    private int humidity;
    private int cloudy;

    public WeatherInfo(double temp, int pressure, int humidity, int cloudy) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.cloudy = cloudy;
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
