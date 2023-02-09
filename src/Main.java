import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HashMap<Integer, Task> tasks= new HashMap<>();
        HashMap<Integer, Epic> epics=new HashMap<>();
        HashMap<Integer, Subtask> subtasks1=new HashMap<>();
        HashMap<Integer, Subtask> subtasks2=new HashMap<>();
        Manager manager=new Manager();

        manager.addTask(tasks, new Task("Купить продукты", "Сходить в магазин", Task.Status.NEW));
        manager.addTask(tasks, new Task("Приготовить ужин", "Фрикасе из кролика",
                Task.Status.IN_PROGRESS));
        manager.addTask(subtasks1, new Subtask("Изучить теорию", "Закрыть теорию спринта за неделю",
                Task.Status.IN_PROGRESS));
        manager.addTask(subtasks1, new Subtask("Написать проект", "За вторую неделю сдать проект",
                Task.Status.IN_PROGRESS));
        manager.addTask(epics, new Epic("Закрыть спринт 3", "Тема ООП", Task.Status.IN_PROGRESS,
                subtasks1));
        manager.addTask(subtasks2, new Subtask("Прочитать книгу", "Унесенные ветром",
                Task.Status.DONE));
        manager.addTask(epics, new Epic("Написать отзыв на книгу", "500 слов",
                Task.Status.DONE, subtasks2));

        manager.getAllTasks(tasks);
        manager.getAllTasks(subtasks1);
        manager.getAllTasks(epics);
        manager.getAllTasks(subtasks2);

        subtasks1.get(3).setStatus(Task.Status.DONE);
        subtasks1.get(4).setStatus(Task.Status.DONE);
        manager.updateTask(subtasks1, 3, subtasks1.get(3));
        manager.updateTask(subtasks1, 4, subtasks1.get(4));
        manager.updateEpics(epics.get(5));

        System.out.println(manager.getTask(tasks, epics, subtasks1, subtasks2, 4).getName());

        manager.deleteTask(tasks, 1);

    }
}
