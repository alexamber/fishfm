package fm.fish.engine.rest.api;

import fm.fish.config.FishFmConfig;
import fm.fish.engine.rest.ServiceFactory;
import fm.fish.pojo.coub.Timeline;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CoubApi {

    ThreadLocal<CoubApi> INSTANCE = ThreadLocal.withInitial(() ->
            ServiceFactory.createService(CoubApi.class, FishFmConfig.I.coubHost()));

    static CoubApi get() {
        return INSTANCE.get();
    }

    @GET("/api/v2/timeline/hot")
    Call<Timeline> getHot(
            @Query("page") int page,
            @Query("per_page") int perPage,
            @Query("order_by") String orderBy,
            @Query("access_token") String accessToken
    );

    @GET("/api/v2/timeline/tag/{tag}")
    Call<Timeline> getTagged(
            @Path("tag") String tag,
            @Query("page") int page,
            @Query("per_page") int perPage,
            @Query("order_by") String orderBy,
            @Query("access_token") String accessToken
    );
}
