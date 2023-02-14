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

        taskManager.addEpic(new Epic("Написать отзыв на книгу", "500 слов"));
        taskManager.addSubtask(new Subtask("Прочитать книгу", "Унесенные ветром",
                Status.DONE, 6));

        for (int i = 0; i < taskManager.getAllTasks().size(); i++) {
            System.out.println(taskManager.getAllTasks().get(i).getName());
        }

        for (int i = 0; i < taskManager.getAllSubtasks().size(); i++) {
            System.out.println(taskManager.getAllSubtasks().get(i).getName());
        }

        for (int i = 0; i < taskManager.getAllEpics().size(); i++) {
            System.out.println(taskManager.getAllEpics().get(i).getName());
        }

        taskManager.getAllSubtasks().get(0).setStatus(Status.DONE);
        taskManager.getAllSubtasks().get(1).setStatus(Status.DONE);

        taskManager.updateEpic((Epic) taskManager.getOrDefault(3));
        System.out.println(taskManager.getOrDefault(3).getStatus());

        taskManager.deleteTask(1);

        taskManager.add(taskManager.getOrDefault(2));
        taskManager.add(taskManager.getOrDefault(5));
        taskManager.add(taskManager.getOrDefault(6));

        for (int i = 0; i < taskManager.getHistory().size(); i++) {
            System.out.println(taskManager.getHistory().get(i).getName());
        }
    }
}
