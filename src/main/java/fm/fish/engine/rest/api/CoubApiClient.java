package fm.fish.engine.rest.api;

import fm.fish.config.FishFmConfig;
import fm.fish.engine.rest.AbstractApiClient;
import fm.fish.pojo.coub.Timeline;

import static org.apache.http.HttpStatus.SC_OK;

public class CoubApiClient extends AbstractApiClient {

    private static final String TOKEN = FishFmConfig.I.coubToken();

    public static Timeline getTimelineHot() {
        return send(CoubApi.get().getHot(1, 50, "newest_popular", TOKEN), SC_OK).body();
    }

    public static Timeline getTagged(final String tag) {
        return send(CoubApi.get().getTagged(tag, 1, 50, "newest_popular", TOKEN), SC_OK).body();
    }
}
