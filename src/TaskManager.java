import java.util.List;

public interface TaskManager {

    Task getOrDefault(long id);

    void addTask(Task task);

    void addEpic(Epic epic);

    Subtask addSubtask(Subtask subtask);

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubtasks();

    void deleteTask(long id);

    void deleteEpic(long id);

    void deleteSubtask(long id);

    void updateTask(Task task);

    void updateSubtask(Subtask subtask);

    void updateEpic(Epic epic);

    List<Task> getAllTasks();

    List<Epic> getAllEpics();

    List<Subtask> getAllSubtasks();

}

