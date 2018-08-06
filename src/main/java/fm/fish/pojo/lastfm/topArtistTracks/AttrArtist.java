package fm.fish.pojo.lastfm.topArtistTracks;

import com.google.gson.annotations.SerializedName;

public class AttrArtist {

    @SerializedName("artist")
    private String artist;

    @SerializedName("page")
    private String page;

    @SerializedName("perPage")
    private String perPage;

    @SerializedName("totalPages")
    private String totalPages;

    @SerializedName("total")
    private String total;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPerPage() {
        return perPage;
    }

    public void setPerPage(String perPage) {
        this.perPage = perPage;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
