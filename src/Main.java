public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        manager.addTask(new Task("Купить продукты", "Сходить в магазин", Status.NEW));
        manager.addTask(new Task("Приготовить ужин", "Фрикасе из кролика",
                Status.IN_PROGRESS));

        manager.addEpic(new Epic("Закрыть спринт 3", "Тема ООП"));

        manager.addSubtask(new Subtask("Изучить теорию", "Закрыть теорию спринта за неделю",
                Status.IN_PROGRESS, 3));
        manager.addSubtask(new Subtask("Написать проект", "За вторую неделю сдать проект",
                Status.IN_PROGRESS, 3));

        manager.addEpic(new Epic("Написать отзыв на книгу", "500 слов"));
        manager.addSubtask(new Subtask("Прочитать книгу", "Унесенные ветром",
                Status.DONE, 6));

        for (int i=0; i<manager.getAllTasks().size(); i++){
            System.out.println(manager.getAllTasks().get(i).getName());
        }

        for (int i=0; i<manager.getAllSubtasks().size(); i++){
            System.out.println(manager.getAllSubtasks().get(i).getName());
        }

        for (int i=0; i<manager.getAllEpics().size(); i++){
            System.out.println(manager.getAllEpics().get(i).getName());
        }

        manager.getAllSubtasks().get(0).setStatus(Status.DONE);
        manager.getAllSubtasks().get(1).setStatus(Status.DONE);

        manager.updateEpic((Epic) manager.getOrDefault(3));
        System.out.println(manager.getOrDefault(3).getStatus());

        manager.deleteTask(1);
    }
}
