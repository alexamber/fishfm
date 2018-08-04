package fm.fish.engine.rest.api;

import fm.fish.config.FishFmConfig;
import fm.fish.engine.rest.ServiceFactory;
import fm.fish.pojo.openweather.current.WeatherToday;
import fm.fish.pojo.openweather.forecast.WeatherForecast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    ThreadLocal<WeatherApi> INSTANCE = ThreadLocal.withInitial(() ->
            ServiceFactory.createService(WeatherApi.class, FishFmConfig.I.openWeatherHost()));

    static WeatherApi get() {
        return INSTANCE.get();
    }

    @GET("/data/2.5/weather")
    Call<WeatherToday> getWeatherToday(@Query("q") String city,
                                       @Query("units") String units,
                                       @Query("APPID") String token);

    @GET("/data/2.5/forecast")
    Call<WeatherForecast> getWeatherForecast(@Query("q") String city,
                                             @Query("units") String units,
                                             @Query("APPID") String token);

}
