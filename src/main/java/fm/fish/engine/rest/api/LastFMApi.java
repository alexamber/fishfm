package fm.fish.engine.rest.api;

import fm.fish.engine.rest.ServiceFactory;
import fm.fish.pojo.lastfm.topArtist.TopArtistResponse;
import fm.fish.pojo.lastfm.topArtistTracks.TopArtistTrackResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFMApi {

    ThreadLocal<LastFMApi> INSTANCE = ThreadLocal.withInitial(() ->
            ServiceFactory.createService(LastFMApi.class, System.getProperty("lastfm.host", "http://ws.audioscrobbler.com/")));

    static LastFMApi get() {
        return INSTANCE.get();
    }

    @GET("/2.0/?method=tag.gettopartists")
    Call<TopArtistResponse> getTopArtistsByTag(
            @Query("api_key") String api_key,
            @Query("format") String format,
            @Query("tag") String tag
    );

    @GET("/2.0/?method=artist.gettoptracks")
    Call<TopArtistTrackResponse> getTopTracksByArtist(
            @Query("api_key") String api_key,
            @Query("format") String format,
            @Query("artist") String artist
    );


}
