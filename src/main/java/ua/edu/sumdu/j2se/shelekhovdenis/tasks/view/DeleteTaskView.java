package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.controller.CommonController;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.Task;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.TaskIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteTaskView implements View{

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int print(AbstractTaskList taskList) {
        int index = 0;
        CommonController showAllTaskController = new CommonController(new ShowAllTaskView(), 4);

        for( ; ; ) {
            boolean check = false;
            System.out.println("Select the task to be deleted (by index)");
            showAllTaskController.process(taskList);
            try {
                index = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e){
                System.out.println("Incorrect entry, try again!");
                continue;
            } catch (IOException e) {
                logger.error("An error occurred while typing(DeleteTaskView)", e);
            }
            for (int i = 1; i <= taskList.size(); i++) {
                if (index == i)
                    check = true;
            }
            if (!check) {
                System.out.println("Incorrect entry, try again!");
            } else {
                break;
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
