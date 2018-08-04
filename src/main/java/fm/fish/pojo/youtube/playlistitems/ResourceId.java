package fm.fish.pojo.youtube.playlistitems;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ResourceId {

    @SerializedName("kind")
    private String kind;

    @SerializedName("videoId")
    private String videoId;

    public String getKind() {
        return kind;
    }

    public String getVideoId() {
        return videoId;
    }
}