package task.service;

import task.Repeatable;
import task.Task;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TaskService<T extends Task & Repeatable> {

    private final Map<Integer, T> tasks = new HashMap<>();

    public void add(T task) {
        tasks.put(task.getId(), task);
    }

    public TaskService<Task> getTasksByDate(LocalDate date) {

        TaskService<Task> result = new TaskService<>();

        for (T task : tasks.values()) {
            if (task.whenNext() != null && task.whenNext().toLocalDate().equals(date)) {
                result.add(task);
            }
        }
        return result;
    }

    public boolean remove(Integer id) {
        return tasks.remove(id) != null;
    }

    @Override
    public String toString() {
        return tasks.toString();
    }
}
