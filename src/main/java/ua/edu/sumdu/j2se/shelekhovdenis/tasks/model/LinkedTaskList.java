package ua.edu.sumdu.j2se.shelekhovdenis.tasks.model;

import java.util.Iterator;

public class LinkedTaskList extends AbstractTaskList{
    private Node first;
    private Node last;
    private int size;

    public LinkedTaskList(){
        first = null;
        last = null;
        size = 0;
        this.type = ListTypes.types.LINKED;
    }

    @Override
    public void add (Task task) {
        if (task == null) { throw new NullPointerException("The task is null."); }
        if (size != 0) {
            Node prev = last;
            last = new Node(task, null);
            prev.next = last;
        }
        else {
            last = new Node(task, null);
            first = last;
        }
        size++;
    }

    @Override
    public boolean remove (Task task) {
        if (size == 0) { throw new IllegalStateException("The list is empty"); }
        if (task == null) { throw new NullPointerException("The task is empty"); }
        boolean result = false;
        Node prev = first;
        Node curr = first;
        while (curr.next != null || curr == last) {
            if (curr.data == task) {
                // remove the last remaining element
                if (size == 1) { first = null; last = null; }
                // remove first element
                else if (curr == first) { first = first.next; }
                // remove last element
                else if (curr == last) { last = prev; last.next = null; }
                // remove element
                else { prev.next = curr.next; }
                size--;
                result = true;
                break;
            }
            prev = curr;
            curr = prev.next;
        }
        return result;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {

            private Node nextTask = first;
            private Node prevTask = null;

            @Override
            public boolean hasNext() {
                if(nextTask == null){
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public Task next() {
                prevTask = nextTask;
                nextTask = nextTask.next;
                return prevTask.data;
            }

            @Override
            public void remove() {
                if(prevTask == null){
                    throw new IllegalStateException("Method next() has not yet been called");
                } else {
                    LinkedTaskList.this.remove(prevTask.data);
                    prevTask = null;
                }
            }
        };
    }

    private class Node {
        private Task data;
        private Node next;

        public Node(Task data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public Task getTask(int index){
        if( index < 0 || index > size){ throw new IndexOutOfBoundsException("The index is out of range for the list"); }
                LinkedTaskList.Node x = first;
                for (int i = 0; i < index; i++)
                    x = x.next;
                return x.data;
    }

}
