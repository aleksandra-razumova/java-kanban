package com.yandex.app.service;

import com.yandex.app.model.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private final CustomLinkedList<Task> history = new CustomLinkedList<>();
    private final Map<Long, Node<Task>> idAndNodes = new HashMap<>();

    private static class CustomLinkedList<T> {
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
                oldLast.setNext(newNode);
            }
            size++;
        }

        public LinkedList<T> getTasks() { //Собирает все задачи в обычный ArrayList
            LinkedList<T> allOfElements = new LinkedList<>();
            Node <T> current = first;
            while (current != null) {
                allOfElements.add(current.getItem());
                current = current.getNext();
            }
            return allOfElements;
        }

        public void removeNode(Node<T> nodeToRemove) {
            if (nodeToRemove == null) {
                return;
            }
            final Node<T> next = nodeToRemove.getNext();
            final Node<T> prev = nodeToRemove.getPrev();
            if (nodeToRemove == first) {
                first = next;
            }
            if (nodeToRemove == last) {
                last = prev;
            }
            if (prev != null) {
                prev.setNext(next);
            }
            if (next!= null) {
                next.setPrev(prev);
            }
            size--;
        }
    }

    @Override
    public List<Task> getHistory() {
        return history.getTasks();
    }

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }

        if (idAndNodes.containsKey(task.getId())) {
            history.removeNode(idAndNodes.get(task.getId()));
        }

        history.linkLast(task);
        idAndNodes.put(task.getId(), history.last);
    }

    @Override
    public void remove(long id) {
        history.removeNode(idAndNodes.remove(id));
    }
}
