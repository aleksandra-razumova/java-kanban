import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Manager {
    private long generateId;
    private final HashMap<Long, Task> tasks = new HashMap<>();
    private final HashMap<Long, Epic> epics = new HashMap<>();
    private final HashMap<Long, Subtask> subtasks = new HashMap<>();

    public Task getOrDefault(long id) {
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        } else if (epics.containsKey(id)) {
            return epics.get(id);
        } else return subtasks.getOrDefault(id, null);
    }

    public void addTask(Task task) {
        task.setId(++generateId);
        tasks.put(generateId, task);
    }

    public void addEpic(Epic epic) {
        List<Long> subtasks = new ArrayList<>();
        epic.setId(++generateId);
        epics.put(generateId, epic);
    }

    public Subtask addSubtask(Subtask subtask) {
        subtask.setId(++generateId);
        Epic epic = epics.get(subtask.getEpicId());
        epic.getSubtasks().add(subtask.getId());
        subtasks.put(generateId, subtask);
        updateEpic(epic);
        return subtask;
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubtasks().clear();
            updateEpic(epic);
        }
    }

    public void deleteTask(long id) {
        tasks.remove(id);
    }

    public void deleteEpic(long id) {
        for (Subtask subtask : subtasks.values()) {
            if (subtask.getEpicId() == id) {
                subtasks.remove(subtask);
            }
        }
        epics.remove(id);
    }

    public void deleteSubtask(long id) {
        for (Epic epic : epics.values()) {
            if (epic.getSubtasks().get((int)id) == id) {
                epic.getSubtasks().remove(id);
            }
        }
        subtasks.remove(id);
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        updateStatusEpics(epics.get(subtask.getEpicId()));
    }

    public void updateEpic(Epic epic) {
        updateStatusEpics(epic);
        epics.put(epic.getId(), epic);
    }

    private void updateStatusEpics(Epic epic) {
        for (int i = 0; i < epic.getSubtasks().size(); i++) {
            if (subtasks.get(epic.getSubtasks().get(i)).getStatus().equals(Status.IN_PROGRESS)) {
                epic.setStatus(Status.IN_PROGRESS);
            } else if (subtasks.get(epic.getSubtasks().get(i)).getStatus().equals(Status.NEW)) {
                epic.setStatus(Status.NEW);
            } else {
                epic.setStatus(Status.DONE);
            }
        }
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }
}




