package ua.edu.sumdu.j2se.shelekhovDenis.tasks;

public abstract class AbstractTaskList {

    protected ListTypes.types type;

    public abstract void add(Task task);

    public abstract boolean remove (Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    /*public AbstractTaskList incoming (int from, int to) throws Exception {
        for(int i = 0; i < size(); i++) {
            if(getTask(i) != null) {
                if (!(getTask(i).nextTimeAfter(from) <= to && getTask(i).nextTimeAfter(from) >= from)) {
                    this.remove(getTask(i));
                    i--;
                }
            }
        }
        return this;
    }*/

    public AbstractTaskList incoming (int from, int to) throws Exception {
        AbstractTaskList activeTaskList = TaskListFactory.createTaskList(this.type);
        for (int i = 0; i < this.size(); i++) {
            if (this.getTask(i) != null) {
                if (this.getTask(i).nextTimeAfter(from) <= to && this.getTask(i).nextTimeAfter(from) >= from) {
                    activeTaskList.add(this.getTask(i));
                }
            }
        }
        return activeTaskList;
    }
}
