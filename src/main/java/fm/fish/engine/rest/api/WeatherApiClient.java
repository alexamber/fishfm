package fm.fish.engine.rest.api;

import fm.fish.config.FishFmConfig;
import fm.fish.domain.City;
import fm.fish.engine.rest.AbstractApiClient;
import fm.fish.pojo.openweather.current.WeatherToday;
import fm.fish.pojo.openweather.forecast.WeatherForecast;

import static org.apache.http.HttpStatus.SC_OK;

public class WeatherApiClient extends AbstractApiClient {

    private static final String METRIC = "metric";
    private static final String TOKEN = FishFmConfig.I.openWeatherApiKey();

    public static WeatherToday getWeatherToday(final City city) {
        return send(WeatherApi.get().getWeatherToday(city.get(), METRIC, TOKEN), SC_OK).body();
    }

    public static WeatherForecast getWeatherForecast(final City city) {

        return send(WeatherApi.get().getWeatherForecast(city.get(), METRIC, TOKEN), SC_OK).body();
    }
}
