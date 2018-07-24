package fm.fish;

import fm.fish.config.FishFmConfig;
import fm.fish.messenger.*;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import static fm.fish.domain.City.KYIV;
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
                .sleep(ofMinutes(2), ofMinutes(5))
                .hours(8, 21)
                .register(new BlubMessenger(bot, CHAT_ID))
                .register(new WeatherMessenger(bot, CHAT_ID, KYIV))
                .register(new BonAppetitMessenger(bot, CHAT_ID))
                .register(new CatFactMessenger(bot, CHAT_ID))
                .register(new AdviceMessenger(bot, CHAT_ID))
                .register(new CoubMessenger(HOT, bot, CHAT_ID, ofMinutes(193)))
                .register(new CoubMessenger(TAGGED, bot, CHAT_ID, ofMinutes(133)))
                .start();
    }

}
