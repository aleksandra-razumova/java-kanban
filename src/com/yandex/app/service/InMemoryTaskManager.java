package com.yandex.app.service;

import com.yandex.app.model.Epic;
import com.yandex.app.model.Subtask;
import com.yandex.app.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {

    private final HistoryManager historyManager = Managers.getDefaultHistory();
    private long generateId;
    private final Map<Long, Task> tasks = new HashMap<>();
    private final Map<Long, Epic> epics = new HashMap<>();
    private final Map<Long, Subtask> subtasks = new HashMap<>();

    @Override
    public Task getById(long id) {
        Task task = tasks.get(id);
        Subtask subtask = subtasks.get(id);
        Epic epic = epics.get(id);

        if (tasks.containsKey(id)) {
            historyManager.add(task);
            return task;
        } else if (epics.containsKey(id)) {
            historyManager.add(epic);
            return epic;
        } else if (subtasks.containsKey(id)) {
            historyManager.add(subtask);
            return subtask;
        } else {
            return null;
        }
    }

    @Override
    public void addTask(Task task) {
        task.setId(++generateId);
        tasks.put(generateId, task);
    }

    @Override
    public void addEpic(Epic epic) {
        epic.setId(++generateId);
        epics.put(generateId, epic);
    }

    @Override
    public void addSubtask(Subtask subtask) {
        subtask.setId(++generateId);
        Epic epic = epics.get(subtask.getEpicId());
        epic.getSubtasks().add(subtask.getId());
        subtasks.put(generateId, subtask);
        updateStatusEpics(epic);
    }

    @Override
    public void deleteAllTasks() {
        for (Task task: tasks.values() ){
            historyManager.remove(task.getId());
        }
        tasks.clear();

    }

    @Override
    public void deleteAllEpics() {
        for (Task epic: epics.values() ){
            historyManager.remove(epic.getId());
        }
        epics.clear();

        for (Task subtask: subtasks.values() ){
            historyManager.remove(subtask.getId());
        }
        subtasks.clear();
    }

    @Override
    public void deleteAllSubtasks() {
        for (Task subtask: subtasks.values() ){
            historyManager.remove(subtask.getId());
        }
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubtasks().clear();
            updateStatusEpics(epic);
        }
    }

    @Override
    public void deleteTask(long id) {
        tasks.remove(id);
        historyManager.remove(id);
    }

    @Override
    public void deleteEpic(long id) {
        for (Long subtaskId : epics.remove(id).getSubtasks()) {
            subtasks.remove(subtaskId);
            historyManager.remove(subtaskId);
        }
        historyManager.remove(id);
    }

    @Override
    public void deleteSubtask(long id) {
        long epicId = subtasks.remove(id).getEpicId();
        Epic epic = epics.get(epicId);
        epic.getSubtasks().remove(id);
        updateStatusEpics(epic);
        historyManager.remove(id);
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        updateStatusEpics(epics.get(subtask.getEpicId()));
    }

    @Override
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

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

}




