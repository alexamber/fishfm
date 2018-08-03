package fm.fish.pojo.lastfm.topArtistTracks;

import com.google.gson.annotations.SerializedName;
import fm.fish.pojo.lastfm.topArtist.AttrItem;
import fm.fish.pojo.lastfm.topArtist.Image;

import java.util.List;

public class Track {
    @SerializedName("name")
    private String name;

    @SerializedName("playcount")
    private String playcount;

    @SerializedName("listeners")
    private String listeners;

    @SerializedName("mbid")
    private String mbid;

    @SerializedName("url")
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
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

    public TrackArtist getTrackArtist() {
        return trackArtist;
    }

    public void setTrackArtist(TrackArtist trackArtist) {
        this.trackArtist = trackArtist;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public AttrItem getAttrItem() {
        return attrItem;
    }

    public void setAttrItem(AttrItem attrItem) {
        this.attrItem = attrItem;
    }

    @SerializedName("streamable")

    private String streamable;

    @SerializedName("artist")
    private TrackArtist trackArtist;

    @SerializedName("image")
    private List<Image> imageList;

    @SerializedName("@attr")
    private AttrItem attrItem;
}
