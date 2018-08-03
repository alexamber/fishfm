package fm.fish.pojo.lastfm.topArtist;

import com.google.gson.annotations.SerializedName;

public class Attr {

    @SerializedName("tag")
    private String tag;

    @SerializedName("page")
    private String page;

    @SerializedName("perPage")
    private String perPage;

    @SerializedName("totalPages")
    private String totalPages;

    @SerializedName("total")
    private String total;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
