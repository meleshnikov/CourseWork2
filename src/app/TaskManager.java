package app;

import app.view.Menu;
import exceptions.InvalidInputDataException;
import task.*;
import task.service.TaskService;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskManager {

    private final TaskService<Task> tasks = new TaskService<>();

    public void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                Menu.print();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int command = scanner.nextInt();
                    switch (command) {
                        case Menu.ADD_CMD:
                            Task task = inputTask(scanner);
                            if (task != null) {
                                tasks.add(task);
                                System.out.println("Задача успешно добавлена");
                            }
                            break;
                        case Menu.GET_CMD:
                            LocalDate date = inputDate(scanner);
                            System.out.println(tasks.getTasksByDate(date));
                            break;
                        case Menu.REMOVE_CMD:
                            int id = inputID(scanner);
                            if (tasks.remove(id)) {
                                System.out.println("Задача успешно удалена");
                            } else {
                                System.out.println("Задача отсутствует в списке задач!");
                            }
                            break;
                        case Menu.EXIT_CMD:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        } catch (InvalidInputDataException e) {
            System.out.println(e.getMessage());
        }
    }

    private int inputID(Scanner scanner) throws InvalidInputDataException {
        System.out.println("Введите ID задачи:");
        int id;
        if (scanner.hasNextInt()) {
            id = scanner.nextInt();
        } else {
            throw new InvalidInputDataException("Некорректный ID");
        }
        if (id < 1) {
            throw new InvalidInputDataException("ID не может быть отрицательным");
        }
        return id;
    }

    private String inputTaskName(Scanner scanner) throws InvalidInputDataException {
        System.out.print("Введите название задачи: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        if (Task.isValidName(name)) {
            return name;
        } else {
            throw new InvalidInputDataException("Название задачи отсутствует!");
        }
    }

    private String inputTaskDescription(Scanner scanner) throws InvalidInputDataException {
        System.out.print("Введите описание задачи: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        if (Task.isValidDescription(description)) {
            return description;
        } else {
            throw new InvalidInputDataException("Описание задачи отсутствует!");
        }
    }

    private LocalDate inputDate(Scanner scanner) throws InvalidInputDataException {
        LocalDate currentDate = LocalDate.now();
        System.out.println("Введите дату задачи");
        System.out.print("Введите год:");
        int year = scanner.nextInt();
        if (year < currentDate.getYear()) {
            throw new InvalidInputDataException("Некорректный год");
        }
        System.out.print("Введите месяц (1 - 12):");
        int month = scanner.nextInt();
        if (month < 1 || month > 12) {
            throw new InvalidInputDataException("Некорректный месяц (месяц может быть 1 - 12)");
        }
        System.out.print("Введите день месяца (1 - 31):");
        int day = scanner.nextInt();
        if (day < 1 || day > 31) {
            throw new InvalidInputDataException("Некорректный день месяца (день месяца может быть 1 - 31)");
        }
        try {
            return LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            throw new InvalidInputDataException(e.getMessage());
        }
    }

    private LocalTime inputTime(Scanner scanner) throws InvalidInputDataException {
        System.out.println("Введите время задачи");
        System.out.print("Введите час (0 - 23):");
        int hour = scanner.nextInt();
        if (hour < 0 || hour > 23) {
            throw new InvalidInputDataException("Некорректный час (час может быть 0 - 23)");
        }
        System.out.print("Введите минуты (0 - 59):");
        int minute = scanner.nextInt();
        if (minute < 0 || minute > 59) {
            throw new InvalidInputDataException("Некорректные минуты (минуты могут быть 0 - 59)");
        }
        return LocalTime.of(hour, minute);
    }

    private LocalDateTime inputDateTime(Scanner scanner) throws InvalidInputDataException {
        LocalDate date = inputDate(scanner);
        LocalTime time = inputTime(scanner);
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        if (Task.isValidDateTime(dateTime)) {
            return dateTime;
        } else {
            throw new InvalidInputDataException(dateTime + " - недействительная дата");
        }
    }

    private Task.Type inputTaskType(Scanner scanner) throws InvalidInputDataException {
        System.out.println("Выберете тип задачи: ");
        System.out.println("1 Рабочая");
        System.out.println("2 Личная");
        String key = scanner.next();
        switch (key) {
            case "1":
                return Task.Type.Work;
            case "2":
                return Task.Type.Personal;
            default:
                throw new InvalidInputDataException(key + " - пункт меню отсутствует");
        }
    }

    private Task inputTask(Scanner scanner) {
        try {
            String name = inputTaskName(scanner);
            Task.Type type = inputTaskType(scanner);
            LocalDateTime dateTime = inputDateTime(scanner);
            String description = inputTaskDescription(scanner);

            System.out.println("Выберите повторяемость задачи");
            System.out.println("1 Однократная");
            System.out.println("2 Ежедневная");
            System.out.println("3 Еженедельная");
            System.out.println("4 Ежемесячная");
            System.out.println("5 Ежегодная");

            switch (scanner.nextInt()) {
                case 1:
                    return new SingleTask(name, type, dateTime, description);
                case 2:
                    return new DailyTask(name, type, dateTime, description);
                case 3:
                    return new WeeklyTask(name, type, dateTime, description);
                case 4:
                    return new MonthlyTask(name, type, dateTime, description);
                case 5:
                    return new YearlyTask(name, type, dateTime, description);
                default:
                    throw new InvalidInputDataException();
            }

        } catch (InvalidInputDataException | InputMismatchException e) {
            System.out.println(e.getMessage());
            System.out.println("Не удалось создать задачу!");
            System.out.println("Попробуйте снова ...");
            return null;
        }
    }
}
