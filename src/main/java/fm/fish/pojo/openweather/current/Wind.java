package fm.fish.pojo.openweather.current;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Wind {

    @SerializedName("deg")
    private int deg;

    @SerializedName("speed")
    private int speed;

    public int getDeg() {
        return deg;
    }

    public int getSpeed() {
        return speed;
    }
}