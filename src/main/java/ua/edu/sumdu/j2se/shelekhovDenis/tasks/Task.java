package ua.edu.sumdu.j2se.shelekhovDenis.tasks;

public class Task {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean repeated;
    private boolean active;

    public Task(String title, int time){
        if(title == null){ throw new NullPointerException("The title is null"); }
        if(time < 0){ throw new IllegalArgumentException("The calculation parameter must not be negative"); }
        this.title = title;
        this.time = time;
    }

    public Task(String title, int start, int end, int interval){
        if(title == null){ throw new NullPointerException("The title is null"); }
        if(start < 0 || end < start || interval < 0){ throw new IllegalArgumentException("The calculation parameter must not be negative"); }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.repeated = true;
    }

    public  String getTitle(){
        return title;
    }

    public   void setTitle(String title){
        if(title == null){ throw new NullPointerException("The title is null"); }
        this.title = title;
    }

    public  boolean isActive(){
        if(active)
            return true;
        else
            return false;
    }

    public void setActive(boolean active){
        this.active = active;
    }

    public int getTime(){
        if(!repeated)
            return time;
        else
            return start;
    }

    public void setTime(int time){
        if(time < 0){ throw new IllegalArgumentException("The calculation parameter must not be negative"); }
        this.time = time;
        if(repeated)
            repeated = false;
    }

    public int getStartTime(){
        if(repeated)
            return start;
        else
            return time;
    }

    public int getEndTime(){
        if(repeated)
            return end;
        else
            return time;
    }

    public int getRepeatInterval(){
        if(repeated)
            return interval;
        else
            return 0;
    }

    public void setTime(int start, int end, int interval){
        if(!repeated){
            if(start < 0 || end < start || interval < 0){ throw new IllegalArgumentException("The calculation parameter must not be negative"); }
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.repeated = true;
        }
    }

    public boolean isRepeated(){
        if(repeated)
            return true;
        else
            return false;
    }

    public int nextTimeAfter(int current){
        if (current < 0) {throw new IllegalArgumentException("The current it should be 0 or more");}
        if(active){
            if(repeated) {
                for (int i = start; i <= end; i += interval) {
                    if (current < i) {
                        return i;
                    }
                }
            }
            else
                if(current < time)
                    return time;
        }
        return -1;
    }
}
