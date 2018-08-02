package fm.fish.pojo.tmdb.movie.single;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SpokenLanguagesItem {

    @SerializedName("name")
    private String name;

    @SerializedName("iso_639_1")
    private String iso6391;

    public String getName() {
        return name;
    }

    public String getIso6391() {
        return iso6391;
    }
}