package fm.fish.pojo.lastfm.topArtist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Artist {

    @SerializedName("name")
    private String name;

    @SerializedName("mbid")
    private String mbid;

    @SerializedName("url")
    private String url;

    @SerializedName("streamable")
    private String streamable;

    @SerializedName("image")
    private List<Image> images;

    @SerializedName("@attr")
    private AttrItem attrItem;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public AttrItem getAttrItem() {
        return attrItem;
    }

    public void setAttrItem(AttrItem attrItem) {
        this.attrItem = attrItem;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }


}
