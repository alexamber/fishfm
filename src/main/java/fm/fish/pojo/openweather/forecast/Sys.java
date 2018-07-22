package fm.fish.pojo.openweather.forecast;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Sys {

    @SerializedName("pod")
    private String pod;

    public String getPod() {
        return pod;
    }

    @Override
    public String toString() {
        return
                "Sys{" +
                        "pod = '" + pod + '\'' +
                        "}";
    }
}