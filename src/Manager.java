import java.util.HashMap;

public class Manager <T extends Task> {

    public T getTask(HashMap<Integer, Task> tasks, HashMap<Integer, Epic> epics,
                     HashMap<Integer, Subtask> subtasks1, HashMap<Integer, Subtask> subtasks2,
                     Integer id) {

        if (tasks.containsKey(id)){
            return (T) tasks.get(id);
        } else if (epics.containsKey(id)){
            return (T) epics.get(id);
        } else if (subtasks1.containsKey(id)){
            return (T) subtasks1.get(id);
        } else if (subtasks2.containsKey(id)) {
            return (T) subtasks2.get(id);
        }else {
            return null;
        }
    }

   public HashMap<Integer, T> addTask(HashMap<Integer, T> tasks, T task){
    Task.id+=1;
    tasks.put(Task.id, (T) task);
    return tasks;
         }

    public void deleteAllTasks(HashMap<Integer, T> tasks){
        tasks.clear();
    }
    public void deleteTask(HashMap<Integer, T> tasks, int id){
        tasks.remove(id);
    }

    public void updateTask(HashMap<Integer, T> tasks, int id, T task){
        tasks.put(id, (T) task);
    }

    public void updateEpics(Epic epic){

            for (Subtask subtask: epic.getSubtasks().values()){
                if (subtask.getStatus().equals(Task.Status.IN_PROGRESS)){
                    epic.setStatus(Task.Status.IN_PROGRESS);
                } else if (subtask.getStatus().equals(Task.Status.NEW)){
                    epic.setStatus(Task.Status.NEW);
                } else {
                    epic.setStatus(Task.Status.DONE);
                }
            }
        }

    public void getAllTasks(HashMap<Integer, T> tasks){
        for (T task: tasks.values()){
            System.out.println(task.getName()+" "+task.getDescription()+" "+task.getStatus());
        }
    }
}




