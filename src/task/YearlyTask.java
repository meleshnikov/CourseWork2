package task;

import exceptions.InvalidInputDataException;

import java.time.LocalDateTime;

public class YearlyTask extends Task implements Repeatable {

    public YearlyTask(String name,
                      Type type,
                      LocalDateTime dateTime,
                      String description) throws InvalidInputDataException {
        super(name, type, dateTime, description);
    }

    @Override
    public LocalDateTime whenNext() {
        LocalDateTime nextDateTime = getDateTime();
        while (nextDateTime.isBefore(LocalDateTime.now())) {
            nextDateTime = nextDateTime.plusYears(1);
        }
        return nextDateTime;
    }
}
