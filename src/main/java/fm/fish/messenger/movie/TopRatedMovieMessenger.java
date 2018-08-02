package fm.fish.messenger.movie;

import fm.fish.engine.rest.api.TmdbApiClient;
import fm.fish.messenger.AbstractMessenger;
import fm.fish.pojo.tmdb.movie.single.GenresItem;
import fm.fish.pojo.tmdb.movie.single.Movie;
import fm.fish.pojo.tmdb.movie.toprated.TopRatedResultsItem;
import fm.fish.util.CacheUtil;
import fm.fish.util.RandomUtil;
import org.telegram.telegrambots.bots.AbsSender;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TopRatedMovieMessenger extends AbstractMessenger {

    public static final String IMDB_MOVIE_PAGE = "https://www.imdb.com/title/";
    public final static double MIN_VOTE_AVG = 6.5;
    public final static double MIN_VOTES = 300;
    public final static String MOVIE_TIME = "\uD83C\uDFAC\uD83C\uDFA5\uD83C\uDFACMOVIE TIME\uD83C\uDFAC\uD83C\uDFA5\uD83C\uDFAC";
    private final static String TOP_RATED = "Check out one of the best!";
    private final LinkedHashMap<Integer, Integer> cachedTopMovie = CacheUtil.createCache(50);
    private List<TopRatedResultsItem> topRatedResultsItems = getFilteredTopRated(100);

    public TopRatedMovieMessenger(AbsSender bot, long chatId) {
        super(bot, chatId);
    }

    @Override
    protected boolean shouldSend(LocalDateTime now) {
        return every(now, Duration.ofMinutes(80));
    }

    @Override
    public String msgSupplier() {
        int tmdbMovieId = RandomUtil.dice(topRatedResultsItems).getId();
        cachedTopMovie.put(tmdbMovieId, tmdbMovieId);
        Movie movie = TmdbApiClient.getMovie(tmdbMovieId);
        return String.join("\n", MOVIE_TIME, TOP_RATED, movie.getTitle(),
                movie.getGenres().stream().map(GenresItem::getName).collect(Collectors.joining(", ")).toUpperCase(),
                IMDB_MOVIE_PAGE + movie.getImdbId(), movie.getOverview());
    }

    private List<TopRatedResultsItem> getFilteredTopRated(int pages) {
        return TmdbApiClient.getAllTopRated(pages).stream()
                .filter(m -> m.getVoteAverage() > MIN_VOTE_AVG)
                .filter(m -> m.getVoteCount() > MIN_VOTES)
                .filter(m -> !cachedTopMovie.containsKey(m.getId()))
                .collect(Collectors.toList());
    }

}
