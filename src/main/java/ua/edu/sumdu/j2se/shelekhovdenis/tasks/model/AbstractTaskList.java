package ua.edu.sumdu.j2se.shelekhovdenis.tasks.model;

import java.time.LocalDateTime;
import java.util.stream.Stream;
import java.util.Arrays;

/**
 * abstract task list class
 */
public abstract class AbstractTaskList implements Iterable<Task>, Cloneable{

    protected ListTypes.types type;

    /**
     * function of adding a task to the list
     * @param task - the class describes the instance of the task
     */
    public abstract void add(Task task);

    /**
     * function for removing a task from the list
     * @param task  - the class describes the instance of the task
     * @return - "true" - if the task is removinng
     */
    public abstract boolean remove (Task task);

    /**
     * function for determining the number of tasks in the list
     * @return number of tasks in the list
     */
    public abstract int size();

    /**
     * function for getting an instance of a task
     * @param index - serial number of the task
     * @return task
     */
    public abstract Task getTask(int index);

    /**
     * creating a list stream
     * @return list stream from tasks
     */
    public Stream<Task> getStream() {
        return Arrays.stream(this.toArray());
    }

    /**
     * converting a list to an array
     * @return array of tasks
     */
    public Task[] toArray() {
        Task[] tasks = new Task[this.size()];
        for (int i = 0; i < this.size(); i++) {
            tasks[i] = this.getTask(i);
        }
        return tasks;
    }

    /**
     * the function creates a list of tasks that will be performed in a given interval
     * @param from - interval start time
     * @param to - interval end time
     * @return - list of tasks
     * @throws NullPointerException
     */
    public final AbstractTaskList incoming (LocalDateTime from, LocalDateTime to) throws NullPointerException {
        AbstractTaskList activeTaskList = TaskListFactory.createTaskList(this.type);
        this.getStream().filter(task -> (task != null && task.isActive() && task.nextTimeAfter(from) != null &&
                task.nextTimeAfter(from).isBefore(to) && task.nextTimeAfter(from).isAfter(from))).forEach(activeTaskList::add);
        return activeTaskList;
    }

    /**
     * task list hashcode determination function
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hashCode = 5;
        for(int i = 0; i < this.size(); i++){
            hashCode += 17 * (int)Math.pow(this.getTask(i).hashCode(), i);
        }
        return hashCode;
    }

    /**
     * equality check function
     * @param obj - the task to be compared with the given
     * @return "true" - if the tasks are equal, "false" - if the tasks are not equal
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        } else if(obj == null || !(obj instanceof AbstractTaskList)){
            return false;
        }
        AbstractTaskList list = (AbstractTaskList) obj;
        if(list.size() == this.size()) {
            for (int i = 0; i < size(); i++) {
                if (!this.getTask(i).equals(list.getTask(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * task list clone function
     * @return cloned task list
     * @throws CloneNotSupportedException
     */
    @Override
    public AbstractTaskList clone() throws CloneNotSupportedException {
        AbstractTaskList cloneList = TaskListFactory.createTaskList(this.type);
        for(int i = 0; i < this.size(); i++){
            cloneList.add(this.getTask(i));
        }
        return cloneList;
    }

    /**
     * function for displaying information about the task list
     * @return information about the task list
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < size(); i++){
            string.append(this.getTask(i).toString());
        }
        return string.toString();
    }

}
