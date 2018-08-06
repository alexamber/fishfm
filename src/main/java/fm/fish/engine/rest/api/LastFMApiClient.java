package fm.fish.engine.rest.api;

import fm.fish.config.FishFmConfig;
import fm.fish.engine.rest.AbstractApiClient;
import fm.fish.pojo.lastfm.topArtist.TopArtistResponse;
import fm.fish.pojo.lastfm.topArtistTracks.TopArtistTrackResponse;

import static org.apache.http.HttpStatus.SC_OK;

public class LastFMApiClient extends AbstractApiClient {
    private static final String API_KEY = FishFmConfig.I.lastFmApiKey();

    public static TopArtistResponse getArtistByTag(final String tag) {
        return send(LastFMApi.get().getTopArtistsByTag(API_KEY, "json", tag), SC_OK).body();
    }

    public static TopArtistTrackResponse getTopTracksByArtist(final String artist) {
        return send(LastFMApi.get().getTopTracksByArtist(API_KEY, "json", artist), SC_OK).body();
    }

}
