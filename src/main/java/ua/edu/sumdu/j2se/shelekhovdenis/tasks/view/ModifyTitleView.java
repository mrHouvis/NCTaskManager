package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.controller.CommonController;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.TaskIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class ModifyTitleView implements View{

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int print(AbstractTaskList taskList) {
        int index = 0;
        String title ="";
        CommonController showAllTaskController = new CommonController(new ShowAllTaskView(), 4);

        showAllTaskController.process(taskList);
        for( ; ; ) {
            boolean check = false;
            System.out.println("Select the task to be change title (by index)");
            try {
                index = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Incorrect entry, try again!");
                continue;
            } catch (IOException e) {
                logger.error("An error occurred while typing(ModifyTitleView 1)", e);
            }
            for (int i = 1; i <= taskList.size(); i++) {
                if (index == i)
                    check = true;
            }
            if (!check) {
                System.out.println("Incorrect entry, try again!");
                continue;
            }
            System.out.println("Enter a new title");
            try {
                title = reader.readLine();
            } catch (IOException e) {
                logger.error("An error occurred while typing(ModifyTitleView 2)", e);
            }
            taskList.getTask(index - 1).setTitle(title);
            TaskIO.writeText(taskList, new File("TaskList.json"));
            System.out.println("Title changed");
            return 5;
        }
    }

}
