package fm.fish.messenger.youtube;

import com.google.common.collect.Lists;
import fm.fish.config.FishFmConfig;
import fm.fish.engine.rest.api.YoutubeApiClient;
import fm.fish.messenger.AbstractMessenger;
import org.telegram.telegrambots.bots.AbsSender;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FishFmYouTubePlaylistUpdatesMessenger extends AbstractMessenger {

    public static final String YOUTUBE_VIDEO_PAGE = FishFmConfig.I.youTubeVideoPage();
    public final static String FISHEN_TIME = "*\uD83C\uDFA7FISHEN\uD83C\uDFA7TO\uD83C\uDFA7ME\uD83C\uDFA7*";
    public final static int ANCHORS_NUMBER = 3;
    public LinkedList<String> musicUpdates = new LinkedList<>();
    public List<String> anchors = new ArrayList<>();
    public String fishenVideo;

    public FishFmYouTubePlaylistUpdatesMessenger(AbsSender bot, long chatId) {
        super(bot, chatId);
    }

    @Override
    protected boolean shouldSend(LocalDateTime now) {
        if (anchors.isEmpty()) {
            firstRun();
            return false;
        }
        getUpdates();
        return !musicUpdates.isEmpty() && updateFishen();

    }

    @Override
    public String msgSupplier() {
        return String.join("\n", FISHEN_TIME, YOUTUBE_VIDEO_PAGE + fishenVideo);
    }

    private void getUpdates() {
        List<String> latestVideos = YoutubeApiClient.getPlaylist().getItems().stream()
                .map(i -> i.getSnippet().getResourceId().getVideoId()).collect(Collectors.toList());
        String presentAnchor = latestVideos.stream().filter(anchors::contains).findFirst().orElse("");
        List<String> updates = presentAnchor.isEmpty() ? latestVideos : latestVideos.subList(0, latestVideos.indexOf(presentAnchor));
        musicUpdates.addAll(new ArrayList<>(Lists.reverse(updates)));
        anchors = new ArrayList<>(latestVideos.subList(0, ANCHORS_NUMBER));
    }

    private void firstRun() {
        anchors = YoutubeApiClient.getPlaylist().getItems().stream().limit(ANCHORS_NUMBER)
                .map(i -> i.getSnippet().getResourceId().getVideoId()).collect(Collectors.toList());
    }

    private boolean updateFishen() {
        fishenVideo = musicUpdates.removeFirst();
        return true;
    }
}