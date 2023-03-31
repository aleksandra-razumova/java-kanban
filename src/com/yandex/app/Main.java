package com.yandex.app;

import com.yandex.app.model.Epic;
import com.yandex.app.model.Subtask;
import com.yandex.app.model.Task;
import com.yandex.app.service.*;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = Managers.getDefault();

        taskManager.addTask(new Task("Купить продукты", "Сходить в магазин", Status.NEW));
        taskManager.addTask(new Task("Приготовить ужин", "Фрикасе из кролика",
                Status.IN_PROGRESS));

        taskManager.addEpic(new Epic("Закрыть спринт 3", "Тема ООП"));

        taskManager.addSubtask(new Subtask("Изучить теорию", "Закрыть теорию спринта за неделю",
                Status.IN_PROGRESS, 3));
        taskManager.addSubtask(new Subtask("Написать проект", "За вторую неделю сдать проект",
                Status.IN_PROGRESS, 3));
        taskManager.addSubtask(new Subtask("Прочитать книгу", "ООП для чайников",
                Status.DONE, 3));

        taskManager.addEpic(new Epic("Написать отзыв на книгу", "500 слов"));

        taskManager.getOrDefault(2);
        taskManager.getOrDefault(3);
        taskManager.getOrDefault(2);
        taskManager.getOrDefault(5);
        taskManager.getOrDefault(6);
        taskManager.getOrDefault(7);
        taskManager.getOrDefault(2);

        for (int i = 0; i < taskManager.getHistory().size(); i++) {
            System.out.println(taskManager.getHistory().get(i).getName());
        }
        System.out.println("***");
        taskManager.remove(2);

        for (int i = 0; i < taskManager.getHistory().size(); i++) {
            System.out.println(taskManager.getHistory().get(i).getName());
        }
        System.out.println("***");
        taskManager.deleteEpic(3);

        for (int i = 0; i < taskManager.getHistory().size(); i++) {
            System.out.println(taskManager.getHistory().get(i).getName());
        }
    }
}
