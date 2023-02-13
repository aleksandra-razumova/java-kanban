public class Main {
    public static void main(String[] args) {

        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();
        InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();

        inMemoryTaskManager.addTask(new Task("Купить продукты", "Сходить в магазин", Status.NEW));
        inMemoryTaskManager.addTask(new Task("Приготовить ужин", "Фрикасе из кролика",
                Status.IN_PROGRESS));

        inMemoryTaskManager.addEpic(new Epic("Закрыть спринт 3", "Тема ООП"));

        inMemoryTaskManager.addSubtask(new Subtask("Изучить теорию", "Закрыть теорию спринта за неделю",
                Status.IN_PROGRESS, 3));
        inMemoryTaskManager.addSubtask(new Subtask("Написать проект", "За вторую неделю сдать проект",
                Status.IN_PROGRESS, 3));

        inMemoryTaskManager.addEpic(new Epic("Написать отзыв на книгу", "500 слов"));
        inMemoryTaskManager.addSubtask(new Subtask("Прочитать книгу", "Унесенные ветром",
                Status.DONE, 6));

        for (int i = 0; i < inMemoryTaskManager.getAllTasks().size(); i++) {
            System.out.println(inMemoryTaskManager.getAllTasks().get(i).getName());
        }

        for (int i = 0; i < inMemoryTaskManager.getAllSubtasks().size(); i++) {
            System.out.println(inMemoryTaskManager.getAllSubtasks().get(i).getName());
        }

        for (int i = 0; i < inMemoryTaskManager.getAllEpics().size(); i++) {
            System.out.println(inMemoryTaskManager.getAllEpics().get(i).getName());
        }

        inMemoryTaskManager.getAllSubtasks().get(0).setStatus(Status.DONE);
        inMemoryTaskManager.getAllSubtasks().get(1).setStatus(Status.DONE);

        inMemoryTaskManager.updateEpic((Epic) inMemoryTaskManager.getOrDefault(3));
        System.out.println(inMemoryTaskManager.getOrDefault(3).getStatus());

        inMemoryTaskManager.deleteTask(1);

        inMemoryHistoryManager.add(inMemoryTaskManager.getOrDefault(2));
        inMemoryHistoryManager.add(inMemoryTaskManager.getOrDefault(5));
        inMemoryHistoryManager.add(inMemoryTaskManager.getOrDefault(6));

        for (int i = 0; i < inMemoryHistoryManager.getHistory().size(); i++) {
            System.out.println(inMemoryHistoryManager.getHistory().get(i).getName());
        }
    }
}
