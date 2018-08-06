package fm.fish.engine.rest.api;

import fm.fish.config.FishFmConfig;
import fm.fish.engine.rest.ServiceFactory;
import fm.fish.pojo.catfact.CatFact;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CatApi {

    ThreadLocal<CatApi> INSTANCE = ThreadLocal.withInitial(() ->
            ServiceFactory.createService(CatApi.class, FishFmConfig.I.catFactHost()));

    static CatApi get() {
        return INSTANCE.get();
    }

    @GET("/fact")
    Call<CatFact> getFact();


}
