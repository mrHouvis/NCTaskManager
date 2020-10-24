package ua.edu.sumdu.j2se.ShelekhovDenis.tasks;

public class Task {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private int repeated;
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
        this.repeated = (end-start)/interval;
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
        if(repeated == 0)
            return time;
        else
            return start+interval;
    }

    void setTime(int time){
        this.time = time;
        if(repeated != 0)
            repeated = 0;
    }

    int getStartTime(){
        if(repeated != 0)
            return start;
        else
            return time;
    }

    int getEndTime(){
        if(repeated != 0)
            return end;
        else
            return time;
    }

    int getRepeatInterval(){
        if(repeated !=0)
            return interval;
        else
            return 0;
    }

    void setTime(int start, int end, int interval){
        if(repeated == 0){
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.repeated = (end-start)/interval;
        }
    }

    boolean isRepeated(){
        if(repeated != 0)
            return true;
        else
            return false;
    }

    int nextTimeAfter(int current){
        if(repeated != 0)
            for (int i = 1; i <= repeated; i++) {
                if (current >= start + interval * (i - 1) && current < start + interval * i) {
                    return start + interval * i;
                }
            }
        return -1;
    }
}
