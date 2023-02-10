import java.util.HashMap;

public class Manager {
    private int generateId;
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public Task getOneTask (int id){
        if (tasks.containsKey(id)){
            return tasks.get(id);
        } else if (epics.containsKey(id)){
            return (Task) epics.get(id);
        } else if (subtasks.containsKey(id)){
            return (Task) subtasks.get(id);
        } else {
            return null;
        }
    }

    public HashMap<Integer, Task> addTask(Task task){
     task.setId(++generateId);
     tasks.put(generateId, task);
     return tasks;
    }

    public HashMap<Integer, Epic> addEpic(Epic epic){
        epic.setId(++generateId);
        epics.put(generateId, epic);
        return epics;
    }

    public Subtask addSubtask(Subtask subtask, int epicsId){
        subtask.setId(++generateId);
        epics.get(epicsId).getSubtasks().add(subtask);
        subtask.setEpic(epics.get(epicsId));
        subtasks.put(generateId, subtask);
        return subtask;
    }

    public Task changeStatus(int id, Status status) {
        if (tasks.containsKey(id)) {
            tasks.get(id).setStatus(status);
            return tasks.get(id);
        } else if (subtasks.containsKey(id)) {
            subtasks.get(id).setStatus(status);
            return (Task) subtasks.get(id);
        }else{
            return null;
        }
    }

    public void deleteAllTasks(){
        tasks.clear();
    }
    public void deleteAllEpics(){
        epics.clear();
    }
    public void deleteAllSubtasks(){
        subtasks.clear();
    }
    public void deleteTask(int id){
        tasks.remove(id);
    }
    public void deleteEpic(int id){
        epics.remove(id);
    }
    public void deleteSubtask(int id){
        subtasks.remove(id);
    }

    public void updateTask(int id, Task task){
        tasks.put(id, task);
    }
    public void updateSubtask(int id, Subtask subtask){
        tasks.put(id, subtask);
    }
    public void updateEpics(Epic epic){

            for (int i=0; i< epic.getSubtasks().size(); i++){
                if (epic.getSubtasks().get(i).getStatus().equals(Status.IN_PROGRESS)){
                    epic.setStatus(Status.IN_PROGRESS);
                } else if (epic.getSubtasks().get(i).getStatus().equals(Status.NEW)){
                    epic.setStatus(Status.NEW);
                } else {
                    epic.setStatus(Status.DONE);
                }
            }
        }

    public void getAllTasks(){
        for (Task task: tasks.values()){
            System.out.println(task.getName()+" "+task.getDescription()+" "+task.getStatus());
        }
    }
    public void getAllEpics(){
        for (Epic epic: epics.values()){
            System.out.println(epic.getName()+" "+epic.getDescription()+" "+epic.getStatus());
        }
    }
    public void getAllSubtasks(){
        for (Subtask subtask: subtasks.values()){
            System.out.println(subtask.getName()+" "+subtask.getDescription()+" "+subtask.getStatus());
        }
    }
}




