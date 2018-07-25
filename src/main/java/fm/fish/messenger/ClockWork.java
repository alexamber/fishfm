package fm.fish.messenger;

import fm.fish.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ClockWork {

    private static final Logger LOG = LoggerFactory.getLogger(ClockWork.class);
    private final String ZONE_ID = "Europe/Kiev";
    private long minToSleep = Duration.ofMinutes(2).toMillis();
    private long maxToSleep = Duration.ofMinutes(3).toMillis();
    private int startWorkingHour = 0;
    private int endWorkingHour = 24;
    private List<Messenger> messengers = new ArrayList<>();

    public ClockWork() {
    }

    public ClockWork register(Messenger msgr) {
        messengers.add(msgr);
        return this;
    }

    public ClockWork hours(int startHour, int endHour) {
        this.startWorkingHour = startHour;
        this.endWorkingHour = endHour;
        return this;
    }

    public ClockWork sleep(Duration minToSleep, Duration maxToSleep) {
        this.minToSleep = minToSleep.toMillis();
        this.maxToSleep = maxToSleep.toMillis();
        return this;
    }

    public void start() {
        new Thread(() -> {
            while (true) {
                LOG.info("WakeUp!");
                LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of(ZONE_ID)));
                messengers.forEach(m -> {
                    if (now.getHour() >= startWorkingHour && now.getHour() < endWorkingHour)
                        m.wake(now);
                });
                try {
                    long sleep = RandomUtil.R.nextLong(minToSleep, maxToSleep);
                    LOG.info("Sleeping for {}", Duration.ofMillis(sleep).toSeconds());
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
