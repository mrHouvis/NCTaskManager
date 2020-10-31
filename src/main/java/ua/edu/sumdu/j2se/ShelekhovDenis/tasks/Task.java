package ua.edu.sumdu.j2se.ShelekhovDenis.tasks;

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

    String getTitle(){
        return title;
    }

    void setTitle(String title){
        this.title = title;
    }

    boolean isActive(){
        if(active)
            return true;
        else
            return false;
    }

    void setActive(boolean active){
        this.active = active;
    }

    int getTime(){
        if(!repeated)
            return time;
        else
            return start+interval;
    }

    void setTime(int time){
        this.time = time;
        if(repeated)
            repeated = false;
    }

    int getStartTime(){
        if(repeated)
            return start;
        else
            return time;
    }

    int getEndTime(){
        if(repeated)
            return end;
        else
            return time;
    }

    int getRepeatInterval(){
        if(repeated)
            return interval;
        else
            return 0;
    }

    void setTime(int start, int end, int interval){
        if(!repeated){
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.repeated = true;
        }
    }

    boolean isRepeated(){
        if(repeated)
            return true;
        else
            return false;
    }

    int nextTimeAfter(int current){
        if(active){
            if(repeated) {
                for (int i = 1; i <= (end - start) / interval; i++) {
                    if (current >= start + interval * (i - 1) && current < start + interval * i) {
                        return start + interval * i;
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
