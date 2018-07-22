package fm.fish.messenger;

import fm.fish.config.FishFmConfig;
import fm.fish.engine.rest.api.AdviceApiClient;
import fm.fish.util.Phrases;
import org.telegram.telegrambots.bots.AbsSender;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AdviceMessenger extends Messenger {

    private static final int DAY_HOUR_TO_SEND = 10;
    private static final int MINUTE_TO_SEND = 11;
    private static final List<String> ADVICES = Phrases.get(FishFmConfig.I.advicies());
    private LocalDateTime lastTimeSent;

    public AdviceMessenger(AbsSender bot, long chatId) {
        super(bot, chatId);
    }

    @Override
    protected boolean shouldSend(LocalDateTime now) {
        boolean needToSendToday = null == lastTimeSent || !lastTimeSent.toLocalDate().equals(now.toLocalDate());
        if (needToSendToday && now.getHour() >= DAY_HOUR_TO_SEND && now.getMinute() >= MINUTE_TO_SEND) {
            lastTimeSent = now;
            return true;
        }
        return false;
    }

    @Override
    public String msgSupplier() {
        String advices = ADVICES.stream()
                .map(a -> a + ": " + AdviceApiClient.getAdvice().getSlip().getAdvice())
                .collect(Collectors.joining("\n"));
        return "Daily fishvices:\n" + advices;
    }
}
