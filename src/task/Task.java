package task;

import exceptions.InvalidInputDataException;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Task implements Repeatable {
    private static int nextId;
    private final int id = ++nextId;
    private final String name;
    private final Type type;
    private LocalDateTime dateTime;
    private String description;

    public Task(String name,
                Type type,
                LocalDateTime dateTime,
                String description) throws InvalidInputDataException {
        this.name = name;
        this.type = type;
        setDateTime(dateTime);
        setDescription(description);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) throws InvalidInputDataException {
        if (isValidDateTime(dateTime)) {
            this.dateTime = dateTime;
        } else {
            throw new InvalidInputDataException(dateTime + " - недействительная дата");
        }
    }

    public Type getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws InvalidInputDataException {
        if (isValidDescription(description)) {
            this.description = description;
        } else {
            throw new InvalidInputDataException("Описание задачи отсутствует!");
        }
    }

    public enum Type {Personal, Work}

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", data=" + dateTime +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static boolean isValidName(String name) {
        return name != null && !name.isEmpty() && !name.isBlank();
    }

    public static boolean isValidDescription(String description) {
        return description != null && !description.isEmpty() && !description.isBlank();
    }

    public static boolean isValidDateTime(LocalDateTime dateTime) {
        return dateTime != null && dateTime.isAfter(LocalDateTime.now());
    }


}
