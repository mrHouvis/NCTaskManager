package ua.edu.sumdu.j2se.shelekhovDenis.tasks;

public class LinkedTaskList {
    Task[] taskList = new Task[10];

    public void add (Task task) throws NullPointerException {
        boolean overflow = true;
        try {
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
        catch (NullPointerException e){
            System.out.println("The task is empty");
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
        for(int i = 0; i < taskList.length; i++){
            if(taskList[i] == null)
                return i;
        }
        return taskList.length;
    }

    public Task getTask(int index) throws IndexOutOfBoundsException{
        try{
            return taskList[index];
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("The index is out of range for the list");
            return null;
        }
    }

    public LinkedTaskList incoming (int from, int to) throws Exception {
        LinkedTaskList activeTaskList = new LinkedTaskList();
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
