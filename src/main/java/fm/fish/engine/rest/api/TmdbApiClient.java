package fm.fish.engine.rest.api;

import fm.fish.config.FishFmConfig;
import fm.fish.engine.rest.AbstractApiClient;
import fm.fish.pojo.tmdb.movie.nowplaying.NowPlaying;
import fm.fish.pojo.tmdb.movie.nowplaying.NowPlayingResultsItem;
import fm.fish.pojo.tmdb.movie.single.Movie;
import fm.fish.pojo.tmdb.movie.toprated.TopRated;
import fm.fish.pojo.tmdb.movie.toprated.TopRatedResultsItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.http.HttpStatus.SC_OK;

public class TmdbApiClient extends AbstractApiClient {

    private static final String API_KEY = FishFmConfig.I.tmdbApiKey();

    public static NowPlaying getNowPlaying(int page) {
        return sendWithRetry(TmdbApi.get().getMovieNowPlaying(API_KEY, page), SC_OK).body();
    }

    public static TopRated getTopRated(int page) {
        return sendWithRetry(TmdbApi.get().getTopRated(API_KEY, page), SC_OK).body();
    }

    public static Movie getMovie(int id) {
        return sendWithRetry(TmdbApi.get().getMovie(id, API_KEY), SC_OK).body();
    }

    public static List<NowPlayingResultsItem> getAllNowPlaying() {
        NowPlaying nowPlaying = getNowPlaying(1);
        List<NowPlayingResultsItem> results = new ArrayList<>(nowPlaying.getResults());
        IntStream.rangeClosed(2, nowPlaying.getTotalPages()).forEach(p -> results.addAll(getNowPlaying(p).getResults()));
        return results;
    }

    public static List<TopRatedResultsItem> getAllTopRated(int pages) {
        return IntStream.rangeClosed(1, pages)
                .mapToObj(p -> getTopRated(p).getResults()).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
