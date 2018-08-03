package fm.fish.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigCache;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:fishfm.properties"})
public interface FishFmConfig extends Config {

    FishFmConfig I = ConfigCache.getOrCreate(FishFmConfig.class, System.getenv(), System.getProperties());

    @Key("CHAT_ID")
    Long chatId();

    @Key("OPEN_WEATHER_KEY")
    String openWeatherApiKey();

    @Key("BLUBS")
    String blubs();

    @Key("ADVIVIES")
    String advicies();

    @Key("BONAPPETIT")
    String bonappetit();

    @Key("WEATHER_STARTER")
    String weatherStarter();

    @Key("BOT_TOKEN")
    String botToken();

    @Key("BOT_NAME")
    String botName();

    @Key("COUB_TOKEN")
    String coubToken();

    @Key("TMDB_KEY")
    String tmdbApiKey();

    @Key("LAST_FM")
    String lastFmApiKey(); // need to add into fishfm.properties

    @Key("YOUTUBE_KEY")
    String youtubeApiKey(); // need to add into fishfm.properties
}
