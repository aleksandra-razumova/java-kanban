public class Main {
    public static void main(String[] args) {
        Manager manager=new Manager();

        manager.addTask(new Task("Купить продукты", "Сходить в магазин", Status.NEW));
        manager.addTask(new Task("Приготовить ужин", "Фрикасе из кролика",
                Status.IN_PROGRESS));

        manager.addEpic(new Epic("Закрыть спринт 3", "Тема ООП", Status.IN_PROGRESS));

        manager.addSubtask(new Subtask("Изучить теорию", "Закрыть теорию спринта за неделю",
                Status.IN_PROGRESS),3);
        manager.addSubtask(new Subtask("Написать проект", "За вторую неделю сдать проект",
                Status.IN_PROGRESS),3);

        manager.addEpic(new Epic("Написать отзыв на книгу", "500 слов",
                Status.DONE));
        manager.addSubtask(new Subtask("Прочитать книгу", "Унесенные ветром",
                Status.DONE), 6);

        manager.getAllTasks();
        manager.getAllSubtasks();
        manager.getAllEpics();

        manager.changeStatus(4,Status.DONE);
        manager.changeStatus(5,Status.DONE);

        manager.updateEpics((Epic)manager.getOneTask(3));

        manager.deleteTask( 1);
    }
}
