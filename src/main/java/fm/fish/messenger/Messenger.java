package fm.fish.messenger;

import fm.fish.domain.FishSpeaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.time.LocalDateTime;

public abstract class Messenger {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());
    private AbsSender bot;
    private long chatId;

    public Messenger(AbsSender bot, long chatId) {
        this.bot = bot;
        this.chatId = chatId;
    }

    protected abstract boolean shouldSend(LocalDateTime now);

    public abstract String msgSupplier();

    public void wake(LocalDateTime now) {
        if (shouldSend(now)) {
            try {
                String text = FishSpeaker.get() + msgSupplier();
                LOG.info(text);
                bot.execute(new SendMessage().setChatId(chatId).setText(text));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}