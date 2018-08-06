package fm.fish.messenger.youtube;

import fm.fish.config.FishFmConfig;
import fm.fish.engine.rest.api.LastFMApiClient;
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static fm.fish.engine.rest.api.YoutubeApi.VideoDuration;

public class FindNewYoutubeVideoMessenger extends AbstractMessenger {

    private static final String YOUTUBE_URL = FishFmConfig.I.youTubeVideoPage();
    private static final String EMOJIS = StringUtils.spawn("\uD83D\uDC3C", 3);
    private static List<String> GENRES = FishFmConfig.I.genres();
    private final Duration interval;
    private final VideoDuration duration;
    private final LinkedHashMap<String, String> cachedTracks = CacheUtil.createCache(20);
    private final HashMap<String, List<Track>> cashedArtistTrack = new HashMap<>();
    private final HashMap<String, List<Artist>> cashedGenreArtists = new HashMap<>();

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
        return EMOJIS + "PANDA_TUBE" + EMOJIS + "\n" + YOUTUBE_URL + getUniqueYoutubeVideoIds();
    }


    private String getUniqueYoutubeVideoIds() {
        return getYoutubeItem().stream()
                .map(c -> c.getId().getVideoId())
                .findFirst().get();
    }


    private List<Items> getYoutubeItem() {
        List<String> artistsAndTracks = getArtistsAndTracks().stream()
                .filter(c -> !cachedTracks.containsKey(c))
                .collect(Collectors.toList());
        String artistTrack = RandomUtil.dice(artistsAndTracks);
        cachedTracks.put(artistTrack, artistTrack);
        return YoutubeApiClient.getVideoByName(duration, artistTrack).getItems();

    }

    private List<String> getArtistsAndTracks() {

        String genre = RandomUtil.dice(GENRES);
        List<Artist> artists;

        if (!cashedGenreArtists.containsKey(genre)) {
            artists = LastFMApiClient.getArtistByTag(genre).getTopartists().getArtist();
            cashedGenreArtists.put(genre, artists);
        } else {
            artists = cashedGenreArtists.get(genre);
        }

        Artist artist = RandomUtil.dice(artists);
        List<Track> tracks;

        if (!cashedArtistTrack.containsKey(genre)) {
            tracks = LastFMApiClient.getTopTracksByArtist(artist.getName()).getTopTracks().getTrackList();
            cashedArtistTrack.put(artist.getName(), tracks);
        } else {
            tracks = cashedArtistTrack.get(artist.getName());
        }
        return tracks.stream()
                .map(c -> c.getTrackArtist().getName() + " " + c.getName())
                .collect(Collectors.toList());
    }


}
