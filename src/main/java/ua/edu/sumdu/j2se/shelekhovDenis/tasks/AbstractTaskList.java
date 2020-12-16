package ua.edu.sumdu.j2se.shelekhovDenis.tasks;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable{

    protected ListTypes.types type;

    public abstract void add(Task task);

    public abstract boolean remove (Task task);

    public abstract int size();

    public abstract Task getTask(int index);

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
