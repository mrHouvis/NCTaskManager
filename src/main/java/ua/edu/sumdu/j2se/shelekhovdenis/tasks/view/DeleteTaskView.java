package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.Task;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.TaskIO;

import java.io.File;
import java.io.IOException;

/**
 * outputs using the ShowAllTaskView class
 * all tasks, removes the selected task from the list
 */
public class DeleteTaskView extends Constants implements View{


    /**
     * removes a task from the list
     * @param taskList - abstract task list class
     * @return "6" - launching the main view
     */
    @Override
    public int print(AbstractTaskList taskList) {

        check = false;
        index = 0;

        while(!check){
            System.out.println(SELECT_MESSAGE);
            showAllTaskController.process(taskList);
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
        for(Task task : taskList) {
            if (taskList.remove(taskList.getTask(index-1))) {
                System.out.println("Task was deleted");
                break;
            }
        }
        TaskIO.writeText(taskList, new File("TaskList.json"));
        return 6;
    }

}
