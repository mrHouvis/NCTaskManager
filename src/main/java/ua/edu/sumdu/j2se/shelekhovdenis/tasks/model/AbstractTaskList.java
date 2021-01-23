package ua.edu.sumdu.j2se.shelekhovdenis.tasks.model;

import java.time.LocalDateTime;
import java.util.stream.Stream;
import java.util.Arrays;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable{

    protected ListTypes.types type;

    public abstract void add(Task task);

    public abstract boolean remove (Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public Stream<Task> getStream() {
        return Arrays.stream(this.toArray());
    }

    public Task[] toArray() {
        Task[] tasks = new Task[this.size()];
        for (int i = 0; i < this.size(); i++) {
            tasks[i] = this.getTask(i);
        }
        return tasks;
    }

    public final AbstractTaskList incoming (LocalDateTime from, LocalDateTime to) throws NullPointerException {
        AbstractTaskList activeTaskList = TaskListFactory.createTaskList(this.type);
        this.getStream().filter(task -> (task != null && task.isActive() && task.nextTimeAfter(from) != null &&
                task.nextTimeAfter(from).isBefore(to) && task.nextTimeAfter(from).isAfter(from))).forEach(activeTaskList::add);
        return activeTaskList;
    }

    @Override
    public int hashCode() {
        int hashCode = 5;
        for(int i = 0; i < this.size(); i++){
            hashCode += 17 * (int)Math.pow(this.getTask(i).hashCode(), i);
        }
        return hashCode;
    }

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

    @Override
    public AbstractTaskList clone() throws CloneNotSupportedException {
        AbstractTaskList cloneList = TaskListFactory.createTaskList(this.type);
        for(int i = 0; i < this.size(); i++){
            cloneList.add(this.getTask(i));
        }
        return cloneList;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < size(); i++){
            string.append(this.getTask(i).toString());
        }
        return string.toString();
    }

}
