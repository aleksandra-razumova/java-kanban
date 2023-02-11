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
        epic.setId(++generateId);
        epics.put(generateId, epic);
    }

    public Subtask addSubtask(Subtask subtask) {
        subtask.setId(++generateId);
        Epic epic = epics.get(subtask.getEpicId());
        epic.getSubtasks().add(subtask.getId());
        subtasks.put(generateId, subtask);
        updateStatusEpics(epic);
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
            updateStatusEpics(epic);
        }
    }

    public void deleteTask(long id) {
        tasks.remove(id);
    }

    public void deleteEpic(long id) {
        for (Long subtaskId : epics.remove(id).getSubtasks()) {
            subtasks.remove(subtaskId);
        }
    }

    public void deleteSubtask(long id) {
        long epicId = subtasks.remove(id).getEpicId();
        Epic epic = epics.get(epicId);
        epic.getSubtasks().remove(id);
        updateStatusEpics(epic);
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
        int countNew = 0;
        int countDone = 0;
        List<Long> subtaskIds = epic.getSubtasks();

        for (long subtaskId : subtaskIds) {
            Status status = subtasks.get(subtaskId).getStatus();
            if (status.equals(Status.NEW)) {
                countNew++;
            } else if (status.equals(Status.DONE)) {
                countDone++;
            } else {
                epic.setStatus(Status.IN_PROGRESS);
                return;
            }
        }
        if (countNew == subtaskIds.size()) {
            epic.setStatus(Status.NEW);
        } else if (countDone == subtaskIds.size()) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }


    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }
}




