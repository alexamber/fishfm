package fm.fish.engine.rest.api;

import com.google.common.base.CaseFormat;
import fm.fish.config.FishFmConfig;
import fm.fish.engine.rest.ServiceFactory;
import fm.fish.pojo.youtube.playlistitems.PlaylistItems;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeApi {

    ThreadLocal<YoutubeApi> INSTANCE = ThreadLocal.withInitial(() ->
            ServiceFactory.createService(YoutubeApi.class, FishFmConfig.I.googleapisHost()));

    static YoutubeApi get() {
        return INSTANCE.get();
    }

    @GET("/youtube/v3/playlistItems")
    Call<PlaylistItems> getPlaylistItems(
            @Query("key") String apiKey,
            @Query("playlistId") String playlistId,
            @Query("part") Part part,
            @Query("maxResults") int maxResults,
            @Query("orderBy") Order order
    );

    enum VideoDuration {
        ANY, SHORT, MEDIUM, LONG;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    enum Type {
        CHANNEL, PLAYLIST, VIDEO;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    enum Order {
        DATE, RATING, RELEVANCE, TITLE, VIDEO_COUNT, VIEW_COUNT;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    enum Part {
        SNIPPET, CONTENT_DETAILS;

        @Override
        public String toString() {
            return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name());
        }
    }
}
