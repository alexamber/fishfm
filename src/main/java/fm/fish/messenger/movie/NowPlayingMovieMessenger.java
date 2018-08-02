package fm.fish.messenger.movie;

import fm.fish.engine.rest.api.TmdbApiClient;
import fm.fish.messenger.AbstractMessenger;
import fm.fish.pojo.tmdb.movie.nowplaying.NowPlayingResultsItem;
import fm.fish.pojo.tmdb.movie.single.GenresItem;
import fm.fish.pojo.tmdb.movie.single.Movie;
import fm.fish.util.CacheUtil;
import fm.fish.util.RandomUtil;
import org.telegram.telegrambots.bots.AbsSender;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class NowPlayingMovieMessenger extends AbstractMessenger {

    public static final String IMDB_MOVIE_PAGE = "https://www.imdb.com/title/";
    public final static double MIN_VOTE_AVG = 6.4;
    public final static double MIN_VOTES = 100;
    public final static String MOVIE_TIME = "\uD83C\uDFAC\uD83C\uDFA5\uD83C\uDFACMOVIE TIME\uD83C\uDFAC\uD83C\uDFA5\uD83C\uDFAC";
    private final static String ON_AIR = "Some awesome things airing now!";
    private final static int MOVIE_REFREH_PERIOD = 7;
    private final LinkedHashMap<Integer, Integer> cachedNowMovie = CacheUtil.createCache(20);
    private LocalDateTime lastTimeUpdated;
    private List<NowPlayingResultsItem> allNowPlaying = getFilteredNowPlaying();

    public NowPlayingMovieMessenger(AbsSender bot, long chatId) {
        super(bot, chatId);
    }

    @Override
    protected boolean shouldSend(LocalDateTime now) {
        return every(now, Duration.ofHours(3));
    }

    @Override
    public String msgSupplier() {
        if (MOVIE_REFREH_PERIOD < Duration.between(lastTimeUpdated, LocalDateTime.now()).toDays()) {
            allNowPlaying = getFilteredNowPlaying();
        }
        int tmdbMovieId = RandomUtil.dice(allNowPlaying).getId();
        cachedNowMovie.put(tmdbMovieId, tmdbMovieId);
        Movie movie = TmdbApiClient.getMovie(tmdbMovieId);
        return String.join("\n", MOVIE_TIME, ON_AIR,
                movie.getTitle(), movie.getGenres().stream().map(GenresItem::getName).collect(Collectors.joining(", ")).toUpperCase(),
                IMDB_MOVIE_PAGE + movie.getImdbId(), movie.getOverview());
    }

    private List<NowPlayingResultsItem> getFilteredNowPlaying() {
        lastTimeUpdated = LocalDateTime.now();
        return TmdbApiClient.getAllNowPlaying().stream()
                .filter(m -> m.getVoteAverage() > MIN_VOTE_AVG)
                .filter(m -> m.getVoteCount() > MIN_VOTES)
                .filter(m -> !cachedNowMovie.containsKey(m.getId()))
                .collect(Collectors.toList());
    }
}
