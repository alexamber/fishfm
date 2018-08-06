package fm.fish.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigCache;

import java.util.List;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:fishfm.properties"})
public interface FishFmConfig extends Config {

    FishFmConfig I = ConfigCache.getOrCreate(FishFmConfig.class, System.getenv(), System.getProperties());

    @Key("CHAT_ID")
    Long chatId();

    @Key("OPEN_WEATHER_HOST")
    @DefaultValue("http://api.openweathermap.org/")
    String openWeatherHost();

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

    @Key("COUB_HOST")
    @DefaultValue("http://coub.com/")
    String coubHost();

    @Key("COUB_TOKEN")
    String coubToken();

    @Key("COUB_TAGS")
    List<String> coubTags();

    @Key("COUB_VIDEO_URL")
    @DefaultValue("https://coub.com/view/")
    String coubVideoUrl();

    @Key("GOOGLEAPIS_HOST")
    @DefaultValue("https://www.googleapis.com/")
    String googleapisHost();

    @Key("GOOGLEAPIS_API_KEY")
    String googleapisApiKey();

    @Key("FISH_FM_PLAYLIST_ID")
    String fishFmPlaylistId();

    @Key("YOUTUBE_VIDEO_PAGE")
    @DefaultValue("https://www.youtube.com/watch?v=")
    String youTubeVideoPage();

    @Key("CAT_FACT_HOST")
    @DefaultValue("https://catfact.ninja/")
    String catFactHost();

    @Key("ADVICE_API_HOST")
    @DefaultValue("http://api.adviceslip.com/")
    String adviceApiHost();

    @Key("TMDB_HOST")
    @DefaultValue("https://api.themoviedb.org/")
    String tmdbHost();

    @Key("TMDB_KEY")
    String tmdbApiKey();

    @Key("LAST_FM")
    String lastFmApiKey(); // need to add into fishfm.properties

    @Key("YOUTUBE_KEY")
    String youtubeApiKey(); // need to add into fishfm.properties
}
