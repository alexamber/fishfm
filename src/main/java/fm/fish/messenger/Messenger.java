package fm.fish.messenger;

import fm.fish.domain.FishSpeaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Messenger {

    protected Logger LOG = LoggerFactory.getLogger(this.getClass());
    private LocalDateTime lastTimeSent;
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

    protected boolean onceADay(LocalDateTime now, int afterDayHour, int afterHourMinute) {
        boolean needToSendToday = null == lastTimeSent || !lastTimeSent.toLocalDate().equals(now.toLocalDate());
        if (needToSendToday && now.getHour() >= afterDayHour && now.getMinute() >= afterHourMinute) {
            lastTimeSent = now;
            return true;
        }
        return false;
    }

    protected boolean every(LocalDateTime now, Duration interval) {
        boolean needToSendToday = null == lastTimeSent || Duration.between(lastTimeSent, now).compareTo(interval) > 0;
        if (needToSendToday) {
            lastTimeSent = now;
            return true;
        }
        return false;
    }

    protected boolean probability(float percentage) {
        return Math.random() <= percentage / 100;
    }
}