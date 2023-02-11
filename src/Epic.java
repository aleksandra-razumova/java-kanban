import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private final List<Long> subtasks = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description, Status.NEW);
    }

    public List<Long> getSubtasks() {
        return subtasks;
    }
}
