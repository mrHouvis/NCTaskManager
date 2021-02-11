package ua.edu.sumdu.j2se.shelekhovdenis.tasks.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * the class describes the instance of the task
 */
public class Task implements Cloneable, Serializable {

    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean repeated;
    private boolean active = true;

    /**
     * default constructor
     */
    public Task() {
        super();
    }

    /**
     * one-time task constructor
     * @param title - task name
     * @param time - task execution time
     */
    public Task(String title, LocalDateTime time) {
        if (title == null) {
            throw new NullPointerException("The title is null");
        }
        this.title = title;
        this.time = time;
    }

    /**
     * repeatable task constructor
     * @param title - task name
     * @param start - task start time
     * @param end - task end time
     * @param interval - interval between task executions
     *
     */
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        if (title == null) {
            throw new NullPointerException("The title is null");
        }
        if (interval < 0) {
            throw new IllegalArgumentException("The calculation parameter must not be negative");
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }

    /**
     * function to return the task name
     * @return - Get task title
     */
    public String getTitle() {
        return title;
    }

    /**
     * task name change function
     * @param title - task name
     */
    public void setTitle(String title) {
        if (title == null) {
            throw new NullPointerException("The title is null");
        }
        this.title = title;
    }

    /**
     * function for getting the activity status of a task
     * @return "true" - if the task is active, "false" - if the task is inactive
     */
    public boolean isActive() {
        if (active)
            return true;
        else
            return false;
    }

    /**
     * task activity change function
     * @param active - task activity state
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * function to return the task execution time
     * @return time - if it`s one-time task, start - if it`s repeatable task
     */
    public LocalDateTime getTime() {
        if (!repeated)
            return time;
        else
            return start;
    }

    /**
     * a function to convert a task to a one-time
     * @param time - task execution time
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
        if (repeated)
            repeated = false;
    }

    /**
     * function to return the task start time
     * @return start - if it`s repeatable task, time - if it`s one-time task
     */
    public LocalDateTime getStartTime() {
        if (repeated)
            return start;
        else
            return time;
    }

    /**
     * function to return the task end time
     * @return end - if it`s repeatable task, time - if it`s one-time task
     */
    public LocalDateTime getEndTime() {
        if (repeated)
            return end;
        else
            return time;
    }

    /**
     * function to return the interval between task executions
     * @return interval - if it`s repeatable task, "0" - if it`s one-time task
     */
    public int getRepeatInterval() {
        if (repeated)
            return interval;
        else
            return 0;
    }

    /**
     *
     a function to convert a task to a repeatable one
     * @param start - task start time
     * @param end - task end time
     * @param interval - interval between task executions
     */
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        if (!repeated) {
            if (interval < 0) {
                throw new IllegalArgumentException("The calculation parameter must not be negative");
            }
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.repeated = true;
        }
    }

    /**
     * function that returns task repeatability
     * @return "true" - if the task is repeatable, "false" - if the task is one-time
     */
    public boolean isRepeated() {
        if (repeated)
            return true;
        else
            return false;
    }

    /**
     * task hashcode determination function
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hashCode = 5;
        if(isRepeated()) {
            hashCode = (int) Math.pow(17 + (this.end.getHour()) * (this.end.getMinute()) * (this.start.getHour()) * (this.start.getMinute()), this.interval + 1);
        } else {
            hashCode = (int) Math.pow(31, this.time.getHour() * this.time.getMinute() + 1);
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
        } else if(obj == null){
            return false;
        }
        Task task = (Task) obj;
        if((task.isActive() && this.isActive()) || (!task.isActive() && !this.isActive())) {
            if (!task.isRepeated() && !this.isRepeated()) {
                if (task.getTitle().equals(this.getTitle()) && task.time == this.time) {
                    return true;
                } else {
                    return false;
                }
            } else if (task.isRepeated() && this.isRepeated()) {
                if (task.getTitle().equals(this.getTitle()) && task.start == this.start && task.end == this.end && task.interval == this.interval) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * task clone function
     * @return cloned task
     * @throws CloneNotSupportedException
     */
    @Override
    public Task clone() throws CloneNotSupportedException {
        Task cloneTask = new Task();
        cloneTask.repeated = this.isRepeated();
        cloneTask.title = this.getTitle();
        cloneTask.active = this.isActive();
        if(!isRepeated()){
            cloneTask.time = this.getTime();
        } else {
            cloneTask.start = this.getStartTime();
            cloneTask.end = this.getEndTime();
            cloneTask.interval = this.getRepeatInterval();
        }
        return cloneTask;
    }

    /**
     * function for displaying information about the task
     * @return information about the task
     */
    @Override
    public String toString() {
        if(!isRepeated()){
            return "Title: " + getTitle() +
                    " time: " + getTime() +
                    " isActive: " + isActive() + '\n';
        } else {
            return "Title: " + getTitle() +
                    " start time: " + getStartTime() +
                    " end time: " + getEndTime() +
                    " interval: " + getRepeatInterval() +
                    " isActive: " + isActive() + '\n';
        }
    }

    /**
     * function for determining the next task execution
     * @param current - time after which the task should occur
     * @return time - if the task is one-time, next intermediate task execution - if the task is repeated, null - if other options are not suitable
     */
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
            if (repeated) {
                LocalDateTime i = start;
                while(i.isBefore(end) || i.isEqual(end)){
                    if (current.isBefore(i)) {
                        return i;
                    }
                    i = i.plusSeconds(interval);
                }
            } else if (current.isBefore(time)){
                return time;}
        return null;
    }

    /**
     * task writing function
     * @param out - output stream
     */
    public void writeObject(ObjectOutputStream out) {
        try {
            out.writeInt(getTitle().length());
            out.writeUTF(getTitle());
            out.writeBoolean(isActive());
            out.writeInt(getRepeatInterval());
            if (isRepeated()) {
                out.writeLong(getStartTime().toEpochSecond(ZoneOffset.UTC));
                out.writeLong(getEndTime().toEpochSecond(ZoneOffset.UTC));
            } else {
                out.writeLong(getTime().toEpochSecond(ZoneOffset.UTC));
            }
        } catch(IOException e){
            System.out.println("Tasks were not write");
        }
    }

    /**
     * task reading function
     * @param in - input stream
     * @return task
     */
    public Task readObject(ObjectInputStream in) {
        Task task = new Task();
        try {
            int length = in.readInt();
            task.title = in.readUTF();
            task.active = in.readBoolean();
            task.interval = in.readInt();
            if (interval == 0) {
                task.time = LocalDateTime.ofEpochSecond(in.readLong(), 0, ZoneOffset.UTC);
            } else {
                task.start = LocalDateTime.ofEpochSecond(in.readLong(), 0, ZoneOffset.UTC);
                task.end = LocalDateTime.ofEpochSecond(in.readLong(), 0, ZoneOffset.UTC);
            }
        } catch(IOException e){
            System.out.println("Tasks were not read");
        }
        return task;
    }

}
