package fm.fish.engine.rest.api;

import fm.fish.engine.rest.ServiceFactory;
import fm.fish.pojo.openweather.catfact.CatFact;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CatApi {

    ThreadLocal<CatApi> INSTANCE = ThreadLocal.withInitial(() ->
            ServiceFactory.createService(CatApi.class, System.getProperty("catfact.host", "https://catfact.ninja/")));

    static CatApi get() {
        return INSTANCE.get();
    }

    @GET("/fact")
    Call<CatFact> getFact();


}
