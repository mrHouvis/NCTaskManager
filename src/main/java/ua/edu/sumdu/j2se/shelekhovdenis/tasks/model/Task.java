package ua.edu.sumdu.j2se.shelekhovdenis.tasks.model;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Task implements Cloneable, Serializable {

    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean repeated;
    private boolean active = true;

    public Task() {
        super();
    }

    public Task(String title, LocalDateTime time) {
        if (title == null) {
            throw new NullPointerException("The title is null");
        }
        this.title = title;
        this.time = time;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            throw new NullPointerException("The title is null");
        }
        this.title = title;
    }

    public boolean isActive() {
        if (active)
            return true;
        else
            return false;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getTime() {
        if (!repeated)
            return time;
        else
            return start;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
        if (repeated)
            repeated = false;
    }

    public LocalDateTime getStartTime() {
        if (repeated)
            return start;
        else
            return time;
    }

    public LocalDateTime getEndTime() {
        if (repeated)
            return end;
        else
            return time;
    }

    public int getRepeatInterval() {
        if (repeated)
            return interval;
        else
            return 0;
    }

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

    public boolean isRepeated() {
        if (repeated)
            return true;
        else
            return false;
    }

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
