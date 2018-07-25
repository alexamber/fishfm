package fm.fish.messenger;

import fm.fish.engine.rest.api.CatApiClient;
import org.telegram.telegrambots.bots.AbsSender;

import java.time.LocalDateTime;

public class CatFactMessenger extends Messenger {

    public CatFactMessenger(AbsSender bot, long chatId) {
        super(bot, chatId);
    }

    @Override
    protected boolean shouldSend(LocalDateTime now) {
        return probability(1);
    }

    @Override
    public String msgSupplier() {
        return "Fish loves cat! Did u know?\n" + CatApiClient.getCatFact().getFact();
    }
}
