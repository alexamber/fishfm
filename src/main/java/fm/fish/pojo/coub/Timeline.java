package fm.fish.pojo.coub;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
public class Timeline {

    @SerializedName("next")
    private int next;

    @SerializedName("per_page")
    private int perPage;

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("coubs")
    private List<CoubsItem> coubs;

    public int getNext() {
        return next;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<CoubsItem> getCoubs() {
        return coubs;
    }
}