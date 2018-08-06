package fm.fish.pojo.lastfm.topArtist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Topartists {

    @SerializedName("artist")
    private List<Artist> artist;

    @SerializedName("@attr")
    private Attr attr;

    public List<Artist> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist> artist) {
        this.artist = artist;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }
}
