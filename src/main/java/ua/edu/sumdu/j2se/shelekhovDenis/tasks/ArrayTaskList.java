package ua.edu.sumdu.j2se.shelekhovDenis.tasks;

import java.util.ArrayList;

public class ArrayTaskList {

    ArrayList<Task> arrayList = new ArrayList<>();

    public void add (Task task) {
        arrayList.add(task);
    }

    public boolean remove (Task task) {
        if(arrayList.contains(task)){
            for (Task temp: arrayList) {
                if(temp == task){
                    arrayList.remove(temp);
                    return true;
                }
            }
        }
        return false;
    }

    public int size(){
        return arrayList.size();
    }

    public Task getTask(int index){
        return arrayList.get(index);
    }

    public ArrayTaskList incoming (int from, int to){
        ArrayTaskList activeTask = new ArrayTaskList();
        for (Task temp: arrayList) {
                if(temp.nextTimeAfter(from) <= to && temp.nextTimeAfter(from) >= from){
                    activeTask.add(temp);
                }
        }
        return activeTask;
    }
}
