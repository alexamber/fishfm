package fm.fish.pojo.openweather.forecast;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
public class ListItem {

    @SerializedName("dt")
    private int dt;

    @SerializedName("rain")
    private Rain rain;

    @SerializedName("dt_txt")
    private String dtTxt;

    @SerializedName("weather")
    private List<WeatherItem> weather;

    @SerializedName("main")
    private Main main;

    @SerializedName("clouds")
    private Clouds clouds;

    @SerializedName("sys")
    private Sys sys;

    @SerializedName("wind")
    private Wind wind;

    public int getDt() {
        return dt;
    }

    public Rain getRain() {
        return rain;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public List<WeatherItem> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Sys getSys() {
        return sys;
    }

    public Wind getWind() {
        return wind;
    }

    @Override
    public String toString() {
        return
                "ListItem{" +
                        "dt = '" + dt + '\'' +
                        ",rain = '" + rain + '\'' +
                        ",dt_txt = '" + dtTxt + '\'' +
                        ",weather = '" + weather + '\'' +
                        ",main = '" + main + '\'' +
                        ",clouds = '" + clouds + '\'' +
                        ",sys = '" + sys + '\'' +
                        ",wind = '" + wind + '\'' +
                        "}";
    }
}