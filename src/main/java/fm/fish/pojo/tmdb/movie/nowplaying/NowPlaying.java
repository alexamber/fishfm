package fm.fish.pojo.tmdb.movie.nowplaying;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
public class NowPlaying {

    @SerializedName("dates")
    private Dates dates;

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<NowPlayingResultsItem> results;

    @SerializedName("total_results")
    private int totalResults;

    public Dates getDates() {
        return dates;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<NowPlayingResultsItem> getResults() {
        return results;
    }

    public int getTotalResults() {
        return totalResults;
    }
}