package fm.fish.pojo.openweather.current;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
public class WeatherToday {

    @SerializedName("dt")
    private int dt;

    @SerializedName("coord")
    private Coord coord;

    @SerializedName("visibility")
    private int visibility;

    @SerializedName("weather")
    private List<WeatherItem> weather;

    @SerializedName("name")
    private String name;

    @SerializedName("cod")
    private int cod;

    @SerializedName("main")
    private Main main;

    @SerializedName("clouds")
    private Clouds clouds;

    @SerializedName("id")
    private int id;

    @SerializedName("sys")
    private Sys sys;

    @SerializedName("base")
    private String base;

    @SerializedName("wind")
    private Wind wind;

    public int getDt() {
        return dt;
    }

    public Coord getCoord() {
        return coord;
    }

    public int getVisibility() {
        return visibility;
    }

    public List<WeatherItem> getWeather() {
        return weather;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }

    public Main getMain() {
        return main;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public int getId() {
        return id;
    }

    public Sys getSys() {
        return sys;
    }

    public String getBase() {
        return base;
    }

    public Wind getWind() {
        return wind;
    }
}