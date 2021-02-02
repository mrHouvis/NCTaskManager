package ua.edu.sumdu.j2se.shelekhovdenis.tasks.model;

import java.util.Iterator;

/**
 * an array-based task list
 */
public class ArrayTaskList extends AbstractTaskList{
    Task[] taskList = new Task[10];

    /**
     * class constructor, the type of the list is determined during initialization
     */
    public ArrayTaskList(){
        this.type = ListTypes.types.ARRAY;
    }

    @Override
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

    @Override
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

    @Override
    public int size(){
        for(int i = 0; i < taskList.length; i++){
            if(taskList[i] == null)
                return i;
        }
        return taskList.length;
    }

    @Override
    public Task getTask(int index){
        if( index < 0 || index >= taskList.length){ throw new IndexOutOfBoundsException("The index is out of range for the list"); }
        return taskList[index];
    }

    /**
     * method to traverse the list
     * @return oneself
     */
    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {

            private int index = 0;

            /**
             * checks for the presence of the next list item
             * @return "true" - if the next element of the list exists
             */
            @Override
            public boolean hasNext() {
                if(index < size()) {
                    return true;
                } else {
                    return false;
                }
            }

            /**
             * gets the next item in the list
             * @return task instance
             */
            @Override
            public Task next() {
                return ArrayTaskList.this.getTask(index++);
            }

            /**
             * removes the given list item
             */
            @Override
            public void remove() {
                if(index == 0){
                    throw new IllegalStateException("Method next() has not yet been called");
                } else {
                    ArrayTaskList.this.remove(ArrayTaskList.this.getTask(--index));
                }
            }
        };
    }

}
