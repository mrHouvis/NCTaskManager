package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.controller.CommonController;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.TaskIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class ActivateTaskView implements View{

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int print(AbstractTaskList taskList) {
        int index = 0;
        CommonController showAllTaskController = new CommonController(new ShowAllTaskView(), 4);

        showAllTaskController.process(taskList);
        for( ; ; ) {
            boolean check = false;
            System.out.println("Select the task to be deleted (by index)");
            try {
                index = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e){
                System.out.println("Incorrect entry, try again!");
                continue;
            } catch (IOException e) {
                logger.error("An error occurred while typing(ActivateTaskView)", e);
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
        taskList.getTask(index-1).setActive(!taskList.getTask(index-1).isActive());
        TaskIO.writeText(taskList, new File("TaskList.json"));
        if(taskList.getTask(index-1).isActive())
            System.out.println("Task is activated");
        else
            System.out.println("Task is deactivated");
        return 5;
    }

}
