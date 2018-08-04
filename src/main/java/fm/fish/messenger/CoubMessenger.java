package fm.fish.messenger;

import fm.fish.config.FishFmConfig;
import fm.fish.engine.rest.api.CoubApiClient;
import fm.fish.pojo.coub.CoubsItem;
import fm.fish.util.CacheUtil;
import fm.fish.util.RandomUtil;
import fm.fish.util.StringUtils;
import org.telegram.telegrambots.bots.AbsSender;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CoubMessenger extends AbstractMessenger {
    public static final String COUB_URL = FishFmConfig.I.coubVideoUrl();
    public static final String EMOJIS = StringUtils.spawn("\uD83E\uDD18", 3);
    public static final List<String> TAGS = FishFmConfig.I.coubTags();
    private final CoubMessengerType type;
    private final Duration interval;
    private final LinkedHashMap<String, String> cachedCoubs = CacheUtil.createCache(20);

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
        return EMOJIS + "FISHCOUBTIME" + EMOJIS + "\n" + COUB_URL + getUniqueCoubPermalink();
    }

    private String getUniqueCoubPermalink() {
        List<String> permalinks = getCoubs().stream()
                .map(CoubsItem::getPermalink)
                .filter(p -> !cachedCoubs.containsKey(p))
                .collect(Collectors.toList());
        String permalink = RandomUtil.dice(permalinks);
        cachedCoubs.put(permalink, permalink);
        return permalink;
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
