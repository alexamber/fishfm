package fm.fish.pojo.youtube.playlistitems;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
public class PlaylistItems {

    @SerializedName("kind")
    private String kind;

    @SerializedName("pageInfo")
    private PageInfo pageInfo;

    @SerializedName("etag")
    private String etag;

    @SerializedName("items")
    private List<ItemsItem> items;

    public String getKind() {
        return kind;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public String getEtag() {
        return etag;
    }

    public List<ItemsItem> getItems() {
        return items;
    }
}