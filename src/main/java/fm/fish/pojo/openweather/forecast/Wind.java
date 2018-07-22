package fm.fish.pojo.openweather.forecast;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Wind {

    @SerializedName("deg")
    private double deg;

    @SerializedName("speed")
    private double speed;

    public double getDeg() {
        return deg;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return
                "Wind{" +
                        "deg = '" + deg + '\'' +
                        ",speed = '" + speed + '\'' +
                        "}";
    }
}