package fm.fish.messenger;

import org.telegram.telegrambots.bots.AbsSender;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeekDayMessenger extends AbstractMessenger {

    public WeekDayMessenger(AbsSender bot, long chatId) {
        super(bot, chatId);
    }

    @Override
    protected boolean shouldSend(LocalDateTime now) {
        return onceADay(now, 7, 45);
    }

    @Override
    public String msgSupplier() {
        switch (LocalDate.now().getDayOfWeek()) {
            case FRIDAY:
                return "Good morning darling, it's Friday!!!";
            case SATURDAY:
                return "Get up meaty!!! All weekend is on the way!";
            case SUNDAY:
                return "Say hi to Sunday!!!";
            case MONDAY:
                return "No mercy to workdays! Have a great week!";
            case WEDNESDAY:
                return "Have a sunny day! It is Friday soon!!!";
            default:
                return "Hey fluffy, looking great today! Ready to new accomplishments?";
        }
    }

}
