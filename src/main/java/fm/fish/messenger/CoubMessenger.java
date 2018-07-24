package fm.fish.messenger;

import fm.fish.engine.rest.api.CoubApiClient;
import fm.fish.pojo.coub.CoubsItem;
import fm.fish.util.RandomUtil;
import fm.fish.util.StringUtils;
import org.telegram.telegrambots.bots.AbsSender;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class CoubMessenger extends Messenger {
    public static final String COUB_URL = "https://coub.com/view/";
    public static final String EMOJIS = StringUtils.spawn("\uD83E\uDD18", 3);
    public static final List<String> TAGS = Arrays.asList("funnycat", "cat");
    private final CoubMessengerType type;
    private final Duration interval;

    public CoubMessenger(CoubMessengerType type, AbsSender bot, long chatId, Duration interval) {
        super(bot, chatId);
        this.type = type;
        this.interval = interval;
    }

    @Override
    protected boolean shouldSend(LocalDateTime now) {
        return every(now, interval);
    }

    @Override
    public String msgSupplier() {
        return EMOJIS + "FISHCOUBTIME" + EMOJIS + "\n" + COUB_URL + RandomUtil.dice(getCoubs()).getPermalink();
    }

    private List<CoubsItem> getCoubs() {
        switch (type) {
            case HOT:
                return CoubApiClient.getTimelineHot().getCoubs();
            case TAGGED:
                return CoubApiClient.getTagged(RandomUtil.dice(TAGS)).getCoubs();
            default:
                throw new RuntimeException("CoubMessengerType not supported");
        }
    }

    public enum CoubMessengerType {
        HOT, TAGGED
    }
}
