package fm.fish.pojo.openweather.current;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Sys {

    @SerializedName("country")
    private String country;

    @SerializedName("sunrise")
    private int sunrise;

    @SerializedName("sunset")
    private int sunset;

    @SerializedName("id")
    private int id;

    @SerializedName("type")
    private int type;

    @SerializedName("message")
    private double message;

    public String getCountry() {
        return country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public double getMessage() {
        return message;
    }
}