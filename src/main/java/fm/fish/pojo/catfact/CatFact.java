package fm.fish.pojo.catfact;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CatFact {

    @SerializedName("fact")
    private String fact;

    @SerializedName("length")
    private int length;

    public String getFact() {
        return fact;
    }

    public int getLength() {
        return length;
    }
}