package fm.fish.pojo.youtube.playlistitems;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PageInfo {

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("resultsPerPage")
    private int resultsPerPage;

    public int getTotalResults() {
        return totalResults;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }
}