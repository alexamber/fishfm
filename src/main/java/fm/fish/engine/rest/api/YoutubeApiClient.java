package fm.fish.engine.rest.api;

import fm.fish.config.FishFmConfig;
import fm.fish.engine.rest.AbstractApiClient;
import fm.fish.pojo.youtube.YoutubeResponse;
import fm.fish.pojo.youtube.playlistitems.PlaylistItems;

import static fm.fish.engine.rest.api.YoutubeApi.Part;
import static org.apache.http.HttpStatus.SC_OK;


public class YoutubeApiClient extends AbstractApiClient {

    private static final String API_KEY = FishFmConfig.I.googleapisApiKey();
    private static final String FISH_FM_PLAYLIST_ID = FishFmConfig.I.fishFmPlaylistId();

    public static PlaylistItems getPlaylist() {
        return send(YoutubeApi.get().getPlaylistItems(
                API_KEY, FISH_FM_PLAYLIST_ID, Part.SNIPPET, 20), SC_OK).body();
    }


    public static YoutubeResponse getVideoByName(final String duration, final String name) {
        return send(YoutubeApi.get().searchVideoByName(API_KEY, "id", duration, "video", 1, "US", name), SC_OK).body();
    }


}