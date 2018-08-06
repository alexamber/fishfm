package fm.fish.pojo.youtube.playlistitems;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Snippet {

    @SerializedName("playlistId")
    private String playlistId;

    @SerializedName("resourceId")
    private ResourceId resourceId;

    @SerializedName("publishedAt")
    private String publishedAt;

    @SerializedName("description")
    private String description;

    @SerializedName("position")
    private int position;

    @SerializedName("title")
    private String title;

    @SerializedName("thumbnails")
    private Thumbnails thumbnails;

    @SerializedName("channelId")
    private String channelId;

    @SerializedName("channelTitle")
    private String channelTitle;

    public String getPlaylistId() {
        return playlistId;
    }

    public ResourceId getResourceId() {
        return resourceId;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getDescription() {
        return description;
    }

    public int getPosition() {
        return position;
    }

    public String getTitle() {
        return title;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public String getChannelId() {
        return channelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }
}