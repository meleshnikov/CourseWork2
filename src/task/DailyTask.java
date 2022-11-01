package task;

import exceptions.InvalidInputDataException;

import java.time.LocalDateTime;

public class DailyTask extends Task implements Repeatable {

    public DailyTask(String name,
                     Type type,
                     LocalDateTime dateTime,
                     String description) throws InvalidInputDataException {
        super(name, type, dateTime, description);
    }

    @Override
    public LocalDateTime whenNext() {
        LocalDateTime nextDateTime = getDateTime();
        while (nextDateTime.isBefore(LocalDateTime.now())) {
            nextDateTime = nextDateTime.plusDays(1);
        }
        return nextDateTime;
    }
}
