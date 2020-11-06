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
        this.title = title;
        this.time = time;
    }

    public Task(String title, int start, int end, int interval){
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
