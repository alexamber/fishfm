package fm.fish.messenger;

import fm.fish.config.FishFmConfig;
import fm.fish.util.CacheUtil;
import fm.fish.util.Phrases;
import fm.fish.util.RandomUtil;
import org.telegram.telegrambots.bots.AbsSender;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;

public class BlubMessenger extends AbstractMessenger {

    private static final List<String> BLUBS = Phrases.get(FishFmConfig.I.blubs());
    private final LinkedHashMap<String, String> cachedBlubs = CacheUtil.createCache(20);

    public BlubMessenger(AbsSender bot, long chatId) {
        super(bot, chatId);
    }

    @Override
    protected boolean shouldSend(LocalDateTime now) {
        return probability(5);
    }

    @Override
    public String msgSupplier() {
        return getUniqueBlub();
    }

    private String getUniqueBlub() {
        String blub = RandomUtil.dice(BLUBS);
        for (int attempt = 0; attempt < 10 && cachedBlubs.containsKey(blub); attempt++) {
            blub = RandomUtil.dice(BLUBS);
        }
        cachedBlubs.put(blub, blub);
        return blub;
    }
}
