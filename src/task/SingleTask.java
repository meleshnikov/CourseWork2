package task;

import exceptions.InvalidInputDataException;

import java.time.LocalDateTime;

public class SingleTask extends Task implements Repeatable {

    public SingleTask(String name,
                      Type type,
                      LocalDateTime dateTime,
                      String description) throws InvalidInputDataException {
        super(name, type, dateTime, description);
    }

    @Override
    public LocalDateTime whenNext() {
        return getDateTime().isBefore(LocalDateTime.now()) ? null : getDateTime();
    }
}
