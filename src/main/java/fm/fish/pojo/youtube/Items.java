package fm.fish.pojo.youtube;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Iryna_Bartnytska on 8/2/2018.
 */
public class Items {

    @SerializedName("kind")
    private String kind;

    @SerializedName("etag")
    private String etag;

    @SerializedName("id")
    private Id id;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }
}
