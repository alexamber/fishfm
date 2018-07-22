package fm.fish.messenger;

import fm.fish.config.FishFmConfig;
import fm.fish.util.Phrases;
import fm.fish.util.RandomUtil;
import org.telegram.telegrambots.bots.AbsSender;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BonAppetitMessenger extends Messenger {
    private static final int DAY_HOUR_TO_SEND = 13;
    private static final List<String> BONAPPETITS = Phrases.get(FishFmConfig.I.bonappetit());
    private LocalDateTime lastTimeSent;

    public BonAppetitMessenger(AbsSender bot, long chatId) {
        super(bot, chatId);
    }

    @Override
    protected boolean shouldSend(LocalDateTime now) {
        boolean needToSendToday = null == lastTimeSent || !lastTimeSent.toLocalDate().equals(now.toLocalDate());
        if (needToSendToday && now.getHour() >= DAY_HOUR_TO_SEND) {
            lastTimeSent = now;
            return true;
        }
        return false;
    }

    @Override
    public String msgSupplier() {
        String emojis = Stream.generate(Meal::random).limit(3).collect(Collectors.joining(""));
        return "Blub-blub time to eat!\n" + emojis + RandomUtil.dice(BONAPPETITS) + emojis;
    }

    enum Meal {
        POT("\uD83C\uDF72"),
        PAN("\uD83E\uDD58"),
        CARROT("\uD83E\uDD55"),
        TACO("\uD83C\uDF2E"),
        CROISANT("\uD83E\uDD50"),
        BACON("\uD83E\uDD53"),
        BOWL("\uD83C\uDF5C"),
        BURITTO("\uD83C\uDF2F");


        private String s;

        Meal(String s) {
            this.s = s;
        }

        public static String random() {
            return RandomUtil.dice(Arrays.asList(values())).get();
        }

        public String get() {
            return s;
        }

    }
}
