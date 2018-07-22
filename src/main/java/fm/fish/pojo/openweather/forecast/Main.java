package fm.fish.pojo.openweather.forecast;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Main {

    @SerializedName("temp")
    private double temp;

    @SerializedName("temp_min")
    private double tempMin;

    @SerializedName("grnd_level")
    private double grndLevel;

    @SerializedName("temp_kf")
    private double tempKf;

    @SerializedName("humidity")
    private int humidity;

    @SerializedName("pressure")
    private double pressure;

    @SerializedName("sea_level")
    private double seaLevel;

    @SerializedName("temp_max")
    private double tempMax;

    public double getTemp() {
        return temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getGrndLevel() {
        return grndLevel;
    }

    public double getTempKf() {
        return tempKf;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public double getTempMax() {
        return tempMax;
    }

    @Override
    public String toString() {
        return
                "Main{" +
                        "temp = '" + temp + '\'' +
                        ",temp_min = '" + tempMin + '\'' +
                        ",grnd_level = '" + grndLevel + '\'' +
                        ",temp_kf = '" + tempKf + '\'' +
                        ",humidity = '" + humidity + '\'' +
                        ",pressure = '" + pressure + '\'' +
                        ",sea_level = '" + seaLevel + '\'' +
                        ",temp_max = '" + tempMax + '\'' +
                        "}";
    }
}