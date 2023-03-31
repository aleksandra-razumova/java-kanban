package com.yandex.app.service;

import com.yandex.app.model.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private final CustomLinkedList<Task> history = new CustomLinkedList<>();
    private static final int HISTORY_SIZE = 10;
    private final HashMap<Long, Node> idAndNodes = new HashMap<>();

    public static class CustomLinkedList<T> {
        public Node<T> first;
        public Node<T> last;
        private int size = 0;

        public void linkLast(T element) { //добавляет задачу в конец списка

            final Node<T> oldLast = last;
            final Node<T> newNode = new Node<>(oldLast, element, null);
            last = newNode;
            if (oldLast == null) {
                first = newNode;
            } else {
                oldLast.next = newNode;
            }
            size++;
        }

        public ArrayList<Task> getTasks() { //Собирает все задачи в обычный ArrayList
            ArrayList<Task> allOfElements = new ArrayList<>();
            Node current = first;
            while (current != null) {
                allOfElements.add((Task) current.item);
                current = current.next;
            }
            return allOfElements;
        }

        public void removeNode(Node<T> nodeToRemove) {
            if (nodeToRemove == null) {
                return;
            }
            if (nodeToRemove == first) {
                first = nodeToRemove.next;
            }
            if (nodeToRemove == last) {
                last = nodeToRemove.prev;
            }
            if (nodeToRemove.prev != null) {
                nodeToRemove.prev.next = nodeToRemove.next;
            }
            if (nodeToRemove.next != null) {
                nodeToRemove.next.prev = nodeToRemove.prev;
            }
            size--;
        }
    }

    @Override
    public List<Task> getHistory() {
        return List.copyOf(history.getTasks());
    }

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }

        if (idAndNodes.containsKey(task.getId())) {
            history.removeNode(idAndNodes.get(task.getId()));
            idAndNodes.remove(task.getId());
        }

        if (history.size >= HISTORY_SIZE) {
            history.removeNode(history.first);
            idAndNodes.remove(0);
        }
        history.linkLast(task);
        idAndNodes.put(task.getId(), history.last);
    }

    @Override
    public void remove(long id) {
        history.removeNode(idAndNodes.get(id));
        idAndNodes.remove(id);
        history.size--;
    }
}
