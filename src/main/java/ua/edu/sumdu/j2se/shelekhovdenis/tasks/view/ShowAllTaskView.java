package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.Task;

public class ShowAllTaskView implements View{

    @Override
    public int print(AbstractTaskList taskList) {
        int number = 1;
        for(Task task : taskList){
            System.out.println(number + ": " + task.toString());
            number++;
        }
        return 5;
    }

}
