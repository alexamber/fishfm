package fm.fish.pojo.lastfm.topArtist;

import com.google.gson.annotations.SerializedName;

public class TopArtistResponse {


    @SerializedName("topartists")
    private Topartists topartists;

    public Topartists getTopartists() {
        return topartists;
    }

    public void setTopartists(Topartists topartists) {
        this.topartists = topartists;
    }


}
