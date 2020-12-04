package ua.edu.sumdu.j2se.shelekhovDenis.tasks;

public class ArrayTaskList {
    Task[] taskList = new Task[10];

    public void add (Task task) {
        boolean overflow = true;
        if(task == null){ throw new NullPointerException("The task is null"); }
        for (int i = 0; i < taskList.length; i++) {
            if (taskList[i] == null) {
                taskList[i] = task;
                overflow = false;
                break;
            }
        }
        if (overflow) {
            Task[] taskListTemp = new Task[taskList.length * 2];
            for (int i = 0; i < taskList.length; i++) {
                taskListTemp[i] = taskList[i];
            }
            taskListTemp[taskList.length] = task;
            taskList = taskListTemp;
        }
    }

    public boolean remove (Task task) {
        if(task == null){ throw new NullPointerException("The task is null"); }
        for (int i = 0; i < taskList.length; i++){
            if(taskList[i] == task){
                Task[] taskListTemp = new Task[taskList.length - 1];
                for(int j = 0; j < taskListTemp.length; j++) {
                    if (j < i){
                        taskListTemp[j] = taskList[j];
                    }
                    else{
                        taskListTemp[j] = taskList[j + 1];
                    }
                }
                taskList = taskListTemp;
                return true;
            }
        }
        return false;
    }


    public int size(){
        for(int i = 0; i < taskList.length; i++){
            if(taskList[i] == null)
                return i;
        }
        return taskList.length;
    }

    public Task getTask(int index){
        if( index < 0 || index >= taskList.length){ throw new IndexOutOfBoundsException("The index is out of range for the list"); }
        return taskList[index];
    }

    public ArrayTaskList incoming (int from, int to) throws Exception {
        ArrayTaskList activeTaskList = new ArrayTaskList();
        for(int i = 0; i < taskList.length; i++) {
            if(taskList[i] != null) {
                if (taskList[i].nextTimeAfter(from) <= to && taskList[i].nextTimeAfter(from) >= from) {
                    activeTaskList.add(taskList[i]);
                }
            }
        }
        return activeTaskList;
    }
}
