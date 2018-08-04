package fm.fish.engine.rest.api;

import fm.fish.config.FishFmConfig;
import fm.fish.engine.rest.ServiceFactory;
import fm.fish.pojo.tmdb.movie.nowplaying.NowPlaying;
import fm.fish.pojo.tmdb.movie.single.Movie;
import fm.fish.pojo.tmdb.movie.toprated.TopRated;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbApi {

    ThreadLocal<TmdbApi> INSTANCE = ThreadLocal.withInitial(() ->
            ServiceFactory.createService(TmdbApi.class, FishFmConfig.I.tmdbHost()));

    static TmdbApi get() {
        return INSTANCE.get();
    }

    @GET("/3/movie/now_playing")
    Call<NowPlaying> getMovieNowPlaying(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("/3/movie/top_rated")
    Call<TopRated> getTopRated(@Query("api_key") String apiKey, @Query("page") int page);

    @GET("/3/movie/{id}")
    Call<Movie> getMovie(@Path("id") int id, @Query("api_key") String apiKey);

}
