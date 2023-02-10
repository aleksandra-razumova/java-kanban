import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Subtask> subtasks=new ArrayList<>();

    public Epic(String name, String description, Status status) {
        super(name, description, status);
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }
}
