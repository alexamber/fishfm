package fm.fish.pojo.tmdb.movie.single;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class GenresItem {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}