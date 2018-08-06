package fm.fish;

import fm.fish.config.FishFmConfig;
import fm.fish.messenger.*;
import fm.fish.messenger.movie.NowPlayingMovieMessenger;
import fm.fish.messenger.movie.TopRatedMovieMessenger;
import fm.fish.messenger.youtube.FindNewYoutubeVideoMessenger;
import fm.fish.messenger.youtube.FishFmYouTubePlaylistUpdatesMessenger;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import static fm.fish.domain.City.KYIV;
import static fm.fish.engine.rest.api.YoutubeApi.VideoDuration.ANY;
import static fm.fish.messenger.CoubMessenger.CoubMessengerType.HOT;
import static fm.fish.messenger.CoubMessenger.CoubMessengerType.TAGGED;
import static java.time.Duration.ofMinutes;

public class BotMain {

    private static long CHAT_ID = FishFmConfig.I.chatId();

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi tapi = new TelegramBotsApi();
        FishSlave bot = new FishSlave();
        tapi.registerBot(bot);

        new ClockWork()
                .sleep(ofMinutes(1), ofMinutes(3))
                .hours(8, 21)
                .register(new BlubMessenger(bot, CHAT_ID))
                .register(new WeatherMessenger(bot, CHAT_ID, KYIV))
                .register(new BonAppetitMessenger(bot, CHAT_ID))
                .register(new CatFactMessenger(bot, CHAT_ID))
                .register(new AdviceMessenger(bot, CHAT_ID))
                .register(new CoubMessenger(HOT, bot, CHAT_ID, ofMinutes(90)))
                .register(new CoubMessenger(TAGGED, bot, CHAT_ID, ofMinutes(70)))
                .register(new WeekDayMessenger(bot, CHAT_ID))
                .register(new NowPlayingMovieMessenger(bot, CHAT_ID))
                .register(new TopRatedMovieMessenger(bot, CHAT_ID))
                .register(new FishFmYouTubePlaylistUpdatesMessenger(bot, CHAT_ID))
                .register(new FindNewYoutubeVideoMessenger(bot, CHAT_ID, ofMinutes(90), ANY))
                .start();
    }

}
