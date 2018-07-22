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

    private static final long MIN_TO_SLEEP = Duration.ofMinutes(1).toMillis();
    private static final long MAX_TO_SLEEP = Duration.ofMinutes(2).toMillis();
    private static final Logger LOG = LoggerFactory.getLogger(ClockWork.class);
    private static final String ZONE_ID = "Europe/Kiev";
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
        startWorkingHour = startHour;
        endWorkingHour = endHour;
        return this;
    }

    public void start() {
        new Thread(() -> {
            while (true) {
                LOG.info("WakeUp!");
                messengers.forEach(m -> {
                    LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of(ZONE_ID)));
                    if (now.getHour() >= startWorkingHour && now.getHour() <= endWorkingHour)
                        m.wake(now);
                });
                try {
                    Thread.sleep(RandomUtil.R.nextLong(MIN_TO_SLEEP, MAX_TO_SLEEP));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
