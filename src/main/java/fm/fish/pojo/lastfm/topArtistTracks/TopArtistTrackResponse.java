package fm.fish.pojo.lastfm.topArtistTracks;

import com.google.gson.annotations.SerializedName;

public class TopArtistTrackResponse {

    public TopTracks getTopTracks() {
        return topTracks;
    }

    public void setTopTracks(TopTracks topTracks) {
        this.topTracks = topTracks;
    }

    @SerializedName("toptracks")
    private TopTracks topTracks;
}
