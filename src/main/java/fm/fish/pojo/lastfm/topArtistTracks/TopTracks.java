package fm.fish.pojo.lastfm.topArtistTracks;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopTracks {
    @SerializedName("track")
    private List<Track> trackList;

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }

    public AttrArtist getAttrArtist() {
        return attrArtist;
    }

    public void setAttrArtist(AttrArtist attrArtist) {
        this.attrArtist = attrArtist;
    }

    @SerializedName("@attr")
    private AttrArtist attrArtist;
}
