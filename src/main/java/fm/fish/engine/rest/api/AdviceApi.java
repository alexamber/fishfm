package fm.fish.engine.rest.api;

import fm.fish.engine.rest.ServiceFactory;
import fm.fish.pojo.openweather.advice.Advice;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AdviceApi {

    ThreadLocal<AdviceApi> INSTANCE = ThreadLocal.withInitial(() ->
            ServiceFactory.createService(AdviceApi.class, System.getProperty("adviceapi.host", "http://api.adviceslip.com/")));

    static AdviceApi get() {
        return INSTANCE.get();
    }

    @GET("/advice")
    Call<Advice> getAdvice();


}
