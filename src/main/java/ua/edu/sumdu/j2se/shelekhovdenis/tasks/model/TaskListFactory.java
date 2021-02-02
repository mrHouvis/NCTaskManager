package ua.edu.sumdu.j2se.shelekhovdenis.tasks.model;

/**
 * a class that creates a list by type
 */
public class TaskListFactory {

    /**
     * a method that creates a list by type
     * @param type - list type
     * @return copy of the list
     */
    public static AbstractTaskList createTaskList(ListTypes.types type){
        switch (type){
            case ARRAY:
                return new ArrayTaskList();
            case LINKED:
                return new LinkedTaskList();
            default: return null;
        }
    }

}
