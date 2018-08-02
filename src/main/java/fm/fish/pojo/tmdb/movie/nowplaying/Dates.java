package fm.fish.pojo.tmdb.movie.nowplaying;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Dates {

    @SerializedName("maximum")
    private String maximum;

    @SerializedName("minimum")
    private String minimum;

    public String getMaximum() {
        return maximum;
    }

    public String getMinimum() {
        return minimum;
    }

}