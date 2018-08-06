package fm.fish.pojo.youtube.playlistitems;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Thumbnails {

    @SerializedName("standard")
    private Standard standard;

    @SerializedName("default")
    private JsonMemberDefault jsonMemberDefault;

    @SerializedName("high")
    private High high;

    @SerializedName("maxres")
    private Maxres maxres;

    @SerializedName("medium")
    private Medium medium;

    public Standard getStandard() {
        return standard;
    }

    public JsonMemberDefault getJsonMemberDefault() {
        return jsonMemberDefault;
    }

    public High getHigh() {
        return high;
    }

    public Maxres getMaxres() {
        return maxres;
    }

    public Medium getMedium() {
        return medium;
    }
}