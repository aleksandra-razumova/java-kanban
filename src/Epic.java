import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {


    private HashMap<Integer, Subtask> subtasks;
    public HashMap<Integer, Subtask> getSubtasks() {
        return subtasks;
    }
    public Epic(String name, String description, Status status, HashMap<Integer, Subtask> subtasks) {
        super(name, description, status);
        this.subtasks = subtasks;
    }

}
