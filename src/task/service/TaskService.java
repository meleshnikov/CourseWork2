package task.service;

import task.Task;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TaskService {

    private final Map<Integer, Task> tasks = new HashMap<>();

    public void add(Task task) {
        tasks.put(task.getId(), task);
    }

    public TaskService getTasksByDate(LocalDate date) {

        TaskService result = new TaskService();

        for (Task task : tasks.values()) {
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
