package fm.fish.messenger.youtube;

import fm.fish.config.FishFmConfig;
import fm.fish.engine.rest.api.LastFMApiClient;
import fm.fish.engine.rest.api.YoutubeApiClient;
import fm.fish.messenger.AbstractMessenger;
import fm.fish.pojo.lastfm.topArtist.Artist;
import fm.fish.pojo.lastfm.topArtistTracks.Track;
import fm.fish.util.CacheUtil;
import fm.fish.util.Phrases;
import fm.fish.util.RandomUtil;
import fm.fish.util.StringUtils;
import org.telegram.telegrambots.bots.AbsSender;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static fm.fish.engine.rest.api.YoutubeApi.VideoDuration;

public class FindNewYoutubeVideoMessenger extends AbstractMessenger {

    private static final String YOUTUBE_VIDEO_PAGE = FishFmConfig.I.youTubeVideoPage();
    private static final String PANDA_TUBE =
            StringUtils.spawn("\uD83D\uDC3C", 3) + "PANDA TUBE" + StringUtils.spawn("\uD83D\uDC3C", 3);
    private static List<String> GENRES = Phrases.get(FishFmConfig.I.genresFile());
    private final Duration interval;
    private final VideoDuration duration;
    private final LinkedHashMap<String, String> cachedTracks = CacheUtil.createCache(20);
    private final HashMap<String, List<Track>> cachedArtistTrack = new HashMap<>();
    private final HashMap<String, List<Artist>> cachedGenreArtists = new HashMap<>();

    public FindNewYoutubeVideoMessenger(AbsSender bot, long chatId, Duration interval, VideoDuration duration) {
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
        return String.join("\n", PANDA_TUBE, YOUTUBE_VIDEO_PAGE + getYoutubeVideoId());
    }


    private String getYoutubeVideoId() {
        List<String> artistsAndTracks = getArtistsAndTracks().stream()
                .filter(c -> !cachedTracks.containsKey(c))
                .collect(Collectors.toList());
        String artistTrack = RandomUtil.dice(artistsAndTracks);
        cachedTracks.put(artistTrack, artistTrack);
        return YoutubeApiClient.getVideoByName(duration, artistTrack).getItems().get(0).getId().getVideoId();

    }

    private List<String> getArtistsAndTracks() {
        String genre = RandomUtil.dice(GENRES);
        List<Artist> artists = cachedGenreArtists.computeIfAbsent(genre,
                v -> LastFMApiClient.getArtistByTag(genre).getTopartists().getArtist());
        Artist artist = RandomUtil.dice(artists);
        List<Track> tracks = cachedArtistTrack.computeIfAbsent(artist.getName(),
                v -> LastFMApiClient.getTopTracksByArtist(artist.getName()).getTopTracks().getTrackList());
        return tracks.stream()
                .map(c -> c.getTrackArtist().getName() + " " + c.getName())
                .collect(Collectors.toList());
    }
}
