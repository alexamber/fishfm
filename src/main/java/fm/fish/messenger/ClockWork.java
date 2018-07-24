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
    private static final String ZONE_ID = "Europe/Kiev";
    private static long minToSleep = Duration.ofMinutes(2).toMillis();
    private static long maxToSleep = Duration.ofMinutes(3).toMillis();
    private static int startWorkingHour = 0;
    private static int endWorkingHour = 24;
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
                messengers.forEach(m -> {
                    LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of(ZONE_ID)));
                    if (now.getHour() >= startWorkingHour && now.getHour() < endWorkingHour)
                        m.wake(now);
                });
                try {
                    Thread.sleep(RandomUtil.R.nextLong(minToSleep, maxToSleep));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
