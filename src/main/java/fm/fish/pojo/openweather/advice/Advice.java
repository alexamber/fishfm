package fm.fish.pojo.openweather.advice;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Advice {

    @SerializedName("slip")
    private Slip slip;

    public Slip getSlip() {
        return slip;
    }
}