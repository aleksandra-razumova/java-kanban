package com.yandex.app.model;

import com.yandex.app.service.Status;

public class Subtask extends Task {
    private final long epicId;

    public Subtask(String name, String description, Status status, long epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }

    public long getEpicId() {
        return epicId;
    }
}
