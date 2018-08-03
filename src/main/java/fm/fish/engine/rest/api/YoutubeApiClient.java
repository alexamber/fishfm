package fm.fish.engine.rest.api;

import fm.fish.config.FishFmConfig;
import fm.fish.engine.rest.AbstractApiClient;
import fm.fish.pojo.lastfm.topArtist.TopArtistResponse;
import fm.fish.pojo.lastfm.topArtistTracks.TopArtistTrackResponse;
import fm.fish.pojo.youtube.YoutubeResponce;

import static org.apache.http.HttpStatus.SC_OK;

public class YoutubeApiClient extends AbstractApiClient {
    private static final String API_KEY = FishFmConfig.I.youtubeApiKey();

    public static YoutubeResponce getVideoByName(final String duration, final String name) {
        return send(YoutubeApi.get().searchVideoByName(API_KEY,"id",duration, "video",3,"viewcount", "US", name), SC_OK).body();
    }
}
