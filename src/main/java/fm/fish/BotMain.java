package fm.fish;

import fm.fish.config.FishFmConfig;
import fm.fish.domain.City;
import fm.fish.messenger.*;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class BotMain {

    private static long CHAT_ID = FishFmConfig.I.chatId();

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi tapi = new TelegramBotsApi();
        FishSlave bot = new FishSlave();
        tapi.registerBot(bot);

        new ClockWork()
                .hours(8, 21)
                .register(new BlubMessenger(bot, CHAT_ID))
                .register(new WeatherMessenger(bot, CHAT_ID, City.KYIV))
                .register(new BonAppetitMessenger(bot, CHAT_ID))
                .register(new CatFactMessenger(bot, CHAT_ID))
                .register(new AdviceMessenger(bot, CHAT_ID))
                .start();
    }

}
