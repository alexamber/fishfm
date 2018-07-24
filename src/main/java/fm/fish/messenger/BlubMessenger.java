package fm.fish.messenger;

import fm.fish.config.FishFmConfig;
import fm.fish.util.Phrases;
import fm.fish.util.RandomUtil;
import org.telegram.telegrambots.bots.AbsSender;

import java.time.LocalDateTime;
import java.util.List;

public class BlubMessenger extends Messenger {

    private static final List<String> BLUBS = Phrases.get(FishFmConfig.I.blubs());

    public BlubMessenger(AbsSender bot, long chatId) {
        super(bot, chatId);
    }

    @Override
    protected boolean shouldSend(LocalDateTime now) {
        return Math.random() <= 0.02;
    }

    @Override
    public String msgSupplier() {
        return RandomUtil.dice(BLUBS);
    }
}
