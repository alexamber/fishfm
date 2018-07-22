package fm.fish;

import fm.fish.config.FishFmConfig;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramWebhookBot;

public class FishSlave extends TelegramWebhookBot {

    private static final String TOKEN = FishFmConfig.I.botToken();
    private static final String USERNAME = FishFmConfig.I.botName();

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public String getBotPath() {
        return null;
    }
}
