package fm.fish.pojo.lastfm.topArtistTracks;

import com.google.gson.annotations.SerializedName;

public class TrackArtist {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @SerializedName("mbid")

    private String mbid;

    @SerializedName("url")
    private String url;
}
