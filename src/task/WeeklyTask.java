package task;

import exceptions.InvalidInputDataException;

import java.time.LocalDateTime;

public class WeeklyTask extends Task implements Repeatable {

    public WeeklyTask(String name,
                      Type type,
                      LocalDateTime dateTime,
                      String description) throws InvalidInputDataException {
        super(name, type, dateTime, description);
    }

    @Override
    public LocalDateTime whenNext() {
        LocalDateTime nextDateTime = getDateTime();
        while (nextDateTime.isBefore(LocalDateTime.now())) {
            nextDateTime = nextDateTime.plusWeeks(1);
        }
        return nextDateTime;
    }
}
