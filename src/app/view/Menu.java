package app.view;

public class Menu {
    private static final String EXIT_ITEM = "Выход";
    public static final int EXIT_CMD = 0;
    private static final String ADD_ITEM = "Добавить задачу";
    public static final int ADD_CMD = 1;
    private static final String GET_ITEM = "Получить задачу на указанный день";
    public static final int GET_CMD = 2;
    private static final String REMOVE_ITEM = "Удалить задачу";
    public static final int REMOVE_CMD = 3;
    private static final String ITEM_VIEW = "%d %s\n";

    public static void print() {
        System.out.printf(ITEM_VIEW, ADD_CMD, ADD_ITEM);
        System.out.printf(ITEM_VIEW, GET_CMD, GET_ITEM);
        System.out.printf(ITEM_VIEW, REMOVE_CMD, REMOVE_ITEM);
        System.out.printf(ITEM_VIEW, EXIT_CMD, EXIT_ITEM);
    }
}
