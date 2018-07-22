package fm.fish.pojo.openweather.advice;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Slip {

    @SerializedName("advice")
    private String advice;

    @SerializedName("slip_id")
    private String slipId;

    public String getAdvice() {
        return advice;
    }

    public String getSlipId() {
        return slipId;
    }
}