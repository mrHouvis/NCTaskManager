package ua.edu.sumdu.j2se.shelekhovdenis.tasks.model;

import java.time.LocalDateTime;
import java.util.*;

/**
 * class for working with tasks
 */
public class Tasks {

    /**
     * the function creates a list of tasks that will be performed in a given interval
     * @param tasks - task list iterator
     * @param start - interval start time
     * @param end - interval end time
     * @return list of tasks
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        if(start == null || end == null || tasks == null){
            throw new NullPointerException("Arguments is must be not null");
        }
        AbstractTaskList list = new LinkedTaskList();
        for(Task task : tasks){
            LocalDateTime current = task.nextTimeAfter(start);
            if (current == null){
                continue;
            }
            if (task.isActive()) {
                if (current.isBefore(end) || current.isEqual(end)) {
                    list.add(task);
                }
            }
        }
        return list;
    }

    /**
     * creates a map for a list of tasks in a time range
     * @param tasks - task list iterator
     * @param start - interval start time
     * @param end - interval end time
     * @return sorted map
     */
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end){
        if(start == null || end == null || tasks == null){
            throw new NullPointerException("Arguments is must be not null");
        }
        SortedMap<LocalDateTime, Set<Task>> tree = new TreeMap<>();
        for (Task task : tasks) {
            for(LocalDateTime key = task.nextTimeAfter(start); key.isBefore(end) || key.isEqual(end); key = task.nextTimeAfter(key)){
                Set<Task> set = new HashSet<>();
                if(tree.containsKey(key)) {
                    set = tree.get(key);
                }
                set.add(task);
                tree.put(key, set);
            }
        }
        return tree;
    }

}
