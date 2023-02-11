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

        if (epic.getSubtasks().size() != 0) {
            for (int i = 0; i < epic.getSubtasks().size(); i++) {
                if (subtasks.get(epic.getSubtasks().get(i)).getStatus().equals(Status.NEW)) {
                    countNew += 1;
                } else if (subtasks.get(epic.getSubtasks().get(i)).getStatus().equals(Status.DONE)) {
                    countDone += 1;
                } else {
                    epic.setStatus(Status.IN_PROGRESS);
                }
            }
            if (countNew == epic.getSubtasks().size()) {
                epic.setStatus(Status.NEW);
            } else if (countDone == epic.getSubtasks().size()) {
                epic.setStatus(Status.DONE);
            } else {
                epic.setStatus(Status.IN_PROGRESS);
            }
        } else {
            epic.setStatus(Status.NEW);
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




