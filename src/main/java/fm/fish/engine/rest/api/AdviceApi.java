package fm.fish.engine.rest.api;

import fm.fish.config.FishFmConfig;
import fm.fish.engine.rest.ServiceFactory;
import fm.fish.pojo.advice.Advice;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AdviceApi {

    ThreadLocal<AdviceApi> INSTANCE = ThreadLocal.withInitial(() ->
            ServiceFactory.createService(AdviceApi.class, FishFmConfig.I.adviceApiHost()));

    static AdviceApi get() {
        return INSTANCE.get();
    }

    @GET("/advice")
    Call<Advice> getAdvice();

}
