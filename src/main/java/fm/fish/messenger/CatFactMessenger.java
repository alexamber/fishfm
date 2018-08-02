package fm.fish.messenger;

import fm.fish.engine.rest.api.CatApiClient;
import fm.fish.util.CacheUtil;
import org.telegram.telegrambots.bots.AbsSender;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.function.Supplier;

public class CatFactMessenger extends AbstractMessenger {

    private final LinkedHashMap<String, String> cachedFacts = CacheUtil.createCache(20);
    private final Supplier<String> factSupplier = () -> CatApiClient.getCatFact().getFact();

    public CatFactMessenger(AbsSender bot, long chatId) {
        super(bot, chatId);
    }

    @Override
    protected boolean shouldSend(LocalDateTime now) {
        return probability(2);
    }

    @Override
    public String msgSupplier() {
        return "Fish loves cat! Did u know?\n" + getUniqueCatFact();
    }


    private String getUniqueCatFact() {
        String fact = factSupplier.get();
        for (int attempt = 0; attempt < 10 && cachedFacts.containsKey(fact); attempt++) {
            fact = factSupplier.get();
        }
        cachedFacts.put(fact, fact);
        return fact;
    }
}
