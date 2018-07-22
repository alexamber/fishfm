package fm.fish.pojo.openweather.forecast;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Rain {

    @SerializedName("3h")
    private double jsonMember3h;

    public double getJsonMember3h() {
        return jsonMember3h;
    }

    @Override
    public String toString() {
        return
                "Rain{" +
                        "3h = '" + jsonMember3h + '\'' +
                        "}";
    }
}