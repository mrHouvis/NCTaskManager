package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.TaskIO;

import java.io.File;
import java.io.IOException;

/**
 * outputs using the ShowAllTaskView class
 * all tasks, that changes the activity state of the task selected by the user
 */
public class ActivateTaskView extends Constants implements View{

    /**
     * changes the activity state of the task
     * @param taskList - abstract task list class
     * @return "5" - launching the view with modifications
     */
    @Override
    public int print(AbstractTaskList taskList) {

        index = 0;
        check = false;
        showAllTaskController.process(taskList);

        while(!check){
            System.out.println(SELECT_MESSAGE);
            try {
                index = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e){
                System.out.println(INCORRECT_ENTRY_MESSAGE);
                continue;
            } catch (IOException e) {
                logger.error(TEXT_ERROR_MESSAGE, e);
            }
            for (int i = 1; i <= taskList.size(); i++) {
                if (index == i)
                    check = true;
            }
            if (!check) {
                System.out.println(INCORRECT_ENTRY_MESSAGE);
            }
        }
        taskList.getTask(index-1).setActive(!taskList.getTask(index-1).isActive());
        TaskIO.writeText(taskList, new File("TaskList.json"));
        if(taskList.getTask(index-1).isActive())
            System.out.println("Task is activated");
        else
            System.out.println("Task is deactivated");
        return 5;
    }

}
