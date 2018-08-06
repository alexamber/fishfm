package fm.fish.messenger.youtube;

import fm.fish.engine.rest.api.LastFMApiClient;
import fm.fish.engine.rest.api.YoutubeApi;
import fm.fish.engine.rest.api.YoutubeApiClient;
import fm.fish.messenger.AbstractMessenger;
import fm.fish.pojo.lastfm.topArtist.Artist;
import fm.fish.pojo.lastfm.topArtistTracks.Track;
import fm.fish.pojo.youtube.Items;
import fm.fish.util.CacheUtil;
import fm.fish.util.RandomUtil;
import fm.fish.util.StringUtils;
import org.telegram.telegrambots.bots.AbsSender;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class FindNewYoutubeVideoMessenger extends AbstractMessenger {

    public static final String YOUTUBE_URL = "https://www.youtube.com/watch?v=";
    public static final String EMOJIS = StringUtils.spawn("\uD83D\uDC3C", 3);
    public static List<String> GENRES = Arrays.asList("thrash rock", "alternative rock", "progressive rock", "hard rock",
            "classic rock", "rock", "progressive rock", "70s", "heavy metal",
            "thrash metal", "metal", "metalcore",
            "heavy metal", "hardcore", "80s", "blues", "psychedelic",
            "psychedelic rock", "guitar", "jazz", "acoustic", "folk",
            "electronic", "indie", "pop", "chill", "punk", "indie rock",
            "Hip-Hop", "instrumental", "black metal", "soul", "chillout",
            "Classical", "industrial", "punk rock", "japanese", "power metal",
            "post-rock", "german", "funk", "hip hop", "russian", "synthwave");
    private final Duration interval;
    private final YoutubeApi.VideoDuration duration;
    private final LinkedHashMap<String, String> cachedVideos = CacheUtil.createCache(20);

    public FindNewYoutubeVideoMessenger(AbsSender bot, long chatId, Duration interval, YoutubeApi.VideoDuration duration) {
        super(bot, chatId);
        this.interval = interval;
        this.duration = duration;
    }


    @Override
    protected boolean shouldSend(LocalDateTime now) {
        return every(now, interval);
    }

    @Override
    public String msgSupplier() {
        return EMOJIS + "PANDA_TUBE" + EMOJIS + "\n" + YOUTUBE_URL + getUniqueYoutubeVideoIds();
    }


    private String getUniqueYoutubeVideoIds() {
        List<String> permalinks = getYoutubeItems().stream()
                .map(c -> c.getId().getVideoId())
                .filter(p -> !cachedVideos.containsKey(p))
                .collect(Collectors.toList());
        String permalink = RandomUtil.dice(permalinks);
        cachedVideos.put(permalink, permalink);
        return permalink;
    }


    private List<Items> getYoutubeItems() {

        List<String> artistsAndTracks = getArtistsAndTracks();
        switch (duration) {
            case SHORT:
                return YoutubeApiClient.getVideoByName("short", RandomUtil.dice(artistsAndTracks)).getItems();
            case MEDIUM:
                return YoutubeApiClient.getVideoByName("medium", RandomUtil.dice(artistsAndTracks)).getItems();
            default:
                throw new RuntimeException("Youtube messenger duration is not supported");
        }

    }

    public List<String> getArtistsAndTracks() {

        Collections.shuffle(GENRES);
        String genre = GENRES.get(0);

        List<Artist> artists = LastFMApiClient.getArtistByTag(genre).getTopartists().getArtist();

        List<Track> tracks = new ArrayList<>();

        for (Artist artist : artists) {
            tracks.addAll(LastFMApiClient.getTopTracksByArtist(artist.getName()).getTopTracks().getTrackList());
        }

        return tracks.stream()
                .map(c -> c.getTrackArtist().getName() + " " + c.getName())
                .collect(Collectors.toList());
    }
}
