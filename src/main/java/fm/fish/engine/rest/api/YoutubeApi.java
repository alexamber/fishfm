package fm.fish.engine.rest.api;

import fm.fish.engine.rest.ServiceFactory;
import fm.fish.pojo.lastfm.topArtist.TopArtistResponse;
import fm.fish.pojo.lastfm.topArtistTracks.TopArtistTrackResponse;
import fm.fish.pojo.youtube.YoutubeResponce;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeApi {

    ThreadLocal<YoutubeApi> INSTANCE = ThreadLocal.withInitial(() ->
            ServiceFactory.createService(YoutubeApi.class, System.getProperty("youtube.host", "https://www.googleapis.com/youtube/v3/")));

    static YoutubeApi get() {
        return INSTANCE.get();
    }

    @GET("/search")
    Call<YoutubeResponce> searchVideoByName(
            @Query("key") String api_key,
            @Query("part") String part,
            @Query("videoDuration") String videoDuration,
            @Query("type") String type,
            @Query("maxResults") int maxResults,
            @Query("order") String order,
            @Query("regionCode") String regionCode,
            @Query("q") String name
    );
}
