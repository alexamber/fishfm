package fm.fish.pojo.openweather.forecast;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Clouds {

    @SerializedName("all")
    private int all;

    public int getAll() {
        return all;
    }

    @Override
    public String toString() {
        return
                "Clouds{" +
                        "all = '" + all + '\'' +
                        "}";
    }
}