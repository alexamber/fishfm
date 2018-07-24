package fm.fish.pojo.coub;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CoubsItem {

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("type")
    private String type;

    @SerializedName("title")
    private String title;

    @SerializedName("remixes_count")
    private int remixesCount;

    @SerializedName("raw_video_title")
    private String rawVideoTitle;

    @SerializedName("duration")
    private double duration;

    @SerializedName("original_visibility_type")
    private String originalVisibilityType;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("id")
    private int id;

    @SerializedName("views_count")
    private int viewsCount;

    @SerializedName("likes_count")
    private int likesCount;

    @SerializedName("recoubs_count")
    private int recoubsCount;

    @SerializedName("permalink")
    private String permalink;

    @SerializedName("channel_id")
    private int channelId;

    @SerializedName("visibility_type")
    private String visibilityType;

    @SerializedName("position_on_page")
    private int positionOnPage;

    public String getCreatedAt() {
        return createdAt;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public int getRemixesCount() {
        return remixesCount;
    }

    public String getRawVideoTitle() {
        return rawVideoTitle;
    }

    public double getDuration() {
        return duration;
    }

    public String getOriginalVisibilityType() {
        return originalVisibilityType;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public int getId() {
        return id;
    }

    public int getViewsCount() {
        return viewsCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public int getRecoubsCount() {
        return recoubsCount;
    }

    public String getPermalink() {
        return permalink;
    }

    public int getChannelId() {
        return channelId;
    }

    public String getVisibilityType() {
        return visibilityType;
    }

    public int getPositionOnPage() {
        return positionOnPage;
    }
}