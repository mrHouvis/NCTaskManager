package ua.edu.sumdu.j2se.shelekhovDenis.tasks;

public class ArrayTaskList {

    Task[] taskList = {null};

    public void add (Task task) {
        if (taskList[taskList.length - 1] == null){
            taskList[taskList.length - 1] = task;
        }
        else{
            Task[] taskListTemp = new Task[taskList.length + 1];
            for(int i = 0; i < taskList.length; i++){
                taskListTemp[i] = taskList[i];
            }
            taskListTemp[taskListTemp.length - 1] = task;
            taskList = taskListTemp;
        }
    }

    public boolean remove (Task task) {
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
        if(taskList[0] == null){
            return 0;
        }
        return taskList.length;
    }

    public Task getTask(int index){
        return taskList[index];
    }

    public ArrayTaskList incoming (int from, int to){
        ArrayTaskList activeTaskList = new ArrayTaskList();
        for(int i = 0; i < taskList.length; i++) {
            if (taskList[i].nextTimeAfter(from) <= to && taskList[i].nextTimeAfter(from) >= from) {
                activeTaskList.add(taskList[i]);
            }
        }
        return activeTaskList;
    }
}
