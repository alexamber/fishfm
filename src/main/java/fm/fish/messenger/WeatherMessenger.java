package fm.fish.messenger;

import fm.fish.config.FishFmConfig;
import fm.fish.domain.City;
import fm.fish.engine.rest.api.WeatherApiClient;
import fm.fish.pojo.openweather.current.Main;
import fm.fish.pojo.openweather.current.WeatherToday;
import fm.fish.pojo.openweather.forecast.WeatherForecast;
import fm.fish.util.Phrases;
import fm.fish.util.RandomUtil;
import org.telegram.telegrambots.bots.AbsSender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherMessenger extends Messenger {

    private static final String WEATHER_MSG_CORE_TEMAPLATE = "NOW: \uD83C\uDF21%sC | \uD83D\uDCA7%s%% humidity | \uD83C\uDF2C%s";
    private static final List<String> WEATHER_MSG_STARTER = Phrases.get(FishFmConfig.I.weatherStarter());
    private static final int DAY_HOUR_TO_SEND = 8;
    private final City city;
    private LocalDateTime lastTimeSent;

    public WeatherMessenger(AbsSender bot, long chaId, City city) {
        super(bot, chaId);
        this.city = city;
    }

    public static void main(String[] args) throws IOException {
        Files.write(Paths.get("weather-starter.txt"), WEATHER_MSG_STARTER);
    }

    @Override
    protected boolean shouldSend(LocalDateTime now) {
        boolean needToSendToday = null == lastTimeSent || !lastTimeSent.toLocalDate().equals(now.toLocalDate());
        if (needToSendToday && now.getHour() >= DAY_HOUR_TO_SEND) {
            lastTimeSent = now;
            return true;
        }
        return false;
    }

    @Override
    public String msgSupplier() {
        return getWeather(city);
    }


    private String getWeather(City city) {
        WeatherToday weather = WeatherApiClient.getWeatherToday(city);
        WeatherForecast forecast = WeatherApiClient.getWeatherForecast(city);
        List<WeatherIcon> dayForecast = forecast.getList().stream()
                .map(i -> WeatherIcon.get(i.getWeather().get(0).getIcon()))
                .limit(4).collect(Collectors.toList());

        Main main = weather.getMain();
        int temp = (int) main.getTemp();
        int humidity = main.getHumidity();

        WeatherIcon weatherIcon = WeatherIcon.get(weather.getWeather().get(0).getIcon());
        WindLevel windLevel = WindLevel.get(weather.getWind().getSpeed());
        HumidityLevel humidityLevel = HumidityLevel.get(humidity);

        String starter = RandomUtil.dice(WEATHER_MSG_STARTER);
        String ender = getWeatherEnder(TempLevel.get(temp), weatherIcon, windLevel, humidityLevel);
        String forecaster = getWeatherForecaster(dayForecast);
        return String.join("\n", starter,
                String.format(WEATHER_MSG_CORE_TEMAPLATE, temp, humidity, windLevel),
                weatherIcon.getMsg(),
                ender,
                forecaster);
    }

    private String getWeatherForecaster(List<WeatherIcon> forecast) {
        if (new ArrayList(Arrays.asList(WeatherIcon.RAIN, WeatherIcon.THUNDER, WeatherIcon.SUN_RAIN)).retainAll(forecast)) {
            return "Sky may cry throughout the day, mb ☂☂☂?";
        } else {
            return "Lucky \uD83C\uDF40\uD83E\uDD1E\uD83C\uDF40 meaty friends, it shouldn't get any worth.";
        }
    }

    private String getWeatherEnder(TempLevel tempLevel, WeatherIcon weatherIcon, WindLevel windLevel, HumidityLevel humidityLevel) {
        if (Arrays.asList(WeatherIcon.RAIN, WeatherIcon.SUN_RAIN, WeatherIcon.THUNDER).contains(weatherIcon)) {
            return "Honey, should you take an ☂☂☂ today!";
        }
        if (TempLevel.HELL.equals(tempLevel)) {
            return "Bloody-bluby Poseidon, you are living in an owen bodies.";
        }
        if (TempLevel.WARM.equals(tempLevel)) {
            return "Isn't it a heavenly weather out there?";
        } else {
            return "Lovely one, isn't it?";
        }
    }

    public enum WeatherIcon {
        SUN("01", "clear sky", "\u2600"),
        FEW_CLOUDS("02", "few clouds", new String(Character.toChars(0x1F324))),
        SUN_SMALL_CLOUD("03", "scattered clouds", "\u26C5"),
        SUN_CLOUD("04", "broken clouds", new String(Character.toChars(0x1F325))),
        MIST("50", "mist", new String(Character.toChars(0x1F32B))),
        SUN_RAIN("10", "rain", new String(Character.toChars(0x1F326))),
        RAIN("09", "shower rain", new String(Character.toChars(0x1F327))),
        THUNDER("11", "thunderstorm", "\u26C8"),
        SNOW("13", "snow", new String(Character.toChars(0x1F328))),
        UNKNOWN("", "unknown", "¯\\_(ツ)_/¯");

        private String iconCode;
        private String condition;
        private String emoji;

        WeatherIcon(String iconCode, String condition, String c) {
            this.iconCode = iconCode;
            this.condition = condition;
            this.emoji = c;
        }

        public static WeatherIcon get(String iconCode) {
            return Arrays.stream(WeatherIcon.values())
                    .filter(w -> iconCode.contains(w.getIconCode()))
                    .findFirst()
                    .orElse(UNKNOWN);
        }

        public String getIconCode() {
            return iconCode;
        }

        public String getMsg() {
            String emojiRow = new String(new char[3]).replace("\0", emoji);
            return emojiRow + condition.toUpperCase() + emojiRow;
        }

        public String getCondition() {
            return condition;
        }

        public String getEmoji() {
            return emoji;
        }

    }

    private enum TempLevel {
        FREEZER, WINTER_COLD, COLD, WARM, HOT, HELL;

        public static TempLevel get(int temp) {
            if (temp <= -15) {
                return FREEZER;
            } else if (temp <= 0) {
                return WINTER_COLD;
            } else if (temp <= 12) {
                return COLD;
            } else if (temp <= 20) {
                return WARM;
            } else if (temp <= 26) {
                return HOT;
            } else return HELL;
        }

    }

    private enum HumidityLevel {
        LOW, MODERATE, HIGH;

        public static HumidityLevel get(int humidity) {
            if (humidity <= 30) {
                return LOW;
            } else if (humidity <= 50) {
                return MODERATE;
            } else
                return HIGH;
        }

    }

    private enum WindLevel {
        BREEZE, WINDY, STRONG_WIND, HURRICANE;

        public static WindLevel get(int windSpeed) {
            if (windSpeed <= 6) {
                return BREEZE;
            } else if (windSpeed <= 14) {
                return WINDY;
            } else if (windSpeed <= 22) {
                return STRONG_WIND;
            } else
                return HURRICANE;
        }

    }
}
