package ua.edu.sumdu.j2se.shelekhovDenis.tasks;

public class LinkedTaskList {
    private Task task;
    private Node first;
    private Node last;
    private int N;

    public LinkedTaskList(){
        first = null;
        last = null;
        N = 0;
    }

    public void add (Task task) {
        if (task == null) { throw new NullPointerException("The task is null."); }
        if (N != 0) {
            Node prev = last;
            last = new Node(task, null);
            prev.next = last;
        }
        else {
            last = new Node(task, null);
            first = last;
        }
        N++;
    }

    public boolean remove (Task task) {
        if (N == 0) { throw new IllegalStateException("The list is empty"); }
        if (task == null) { throw new NullPointerException("The task is empty"); }
        boolean result = false;
        Node prev = first;
        Node curr = first;
        while (curr.next != null || curr == last) {
            if (curr.data == task) {
                // remove the last remaining element
                if (N == 1) { first = null; last = null; }
                // remove first element
                else if (curr == first) { first = first.next; }
                // remove last element
                else if (curr == last) { last = prev; last.next = null; }
                // remove element
                else { prev.next = curr.next; }
                N--;
                result = true;
                break;
            }
            prev = curr;
            curr = prev.next;
        }
        return result;
    }

    public int size(){
        return N;
    }

    private class Node {
        private Task data;
        private Node next;

        public Node(Task data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public Task getTask(int index){
        if( index < 0 || index > N){ throw new IndexOutOfBoundsException("The index is out of range for the list"); }
                LinkedTaskList.Node x = first;
                for (int i = 0; i < index; i++)
                    x = x.next;
                return x.data;
    }

    public LinkedTaskList incoming (int from, int to) throws Exception {
        LinkedTaskList activeTaskList = new LinkedTaskList();
        LinkedTaskList.Node x = first;
        for(int i = 0; i < N; i++) {
            if (x.data.nextTimeAfter(from) <= to && x.data.nextTimeAfter(from) >= from) {
                activeTaskList.add(x.data);
            }
            x = x.next;
        }
        return activeTaskList;
    }
}
