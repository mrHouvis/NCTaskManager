package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.TaskIO;

import java.io.File;
import java.io.IOException;

/**
 * allows you to change the name of the task
 */
public class ModifyTitleView extends Constants implements View{

    @Override
    public int print(AbstractTaskList taskList) {

        index = 0;
        check = false;
        showAllTaskController.process(taskList);

        for( ; ; ){
            System.out.println(selectMessage);
            try {
                index = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println(incorrectEntryMessage);
                continue;
            } catch (IOException e) {
                logger.error(textErrorMessage, e);
            }
            for (int i = 1; i <= taskList.size(); i++) {
                if (index == i)
                    check = true;
            }
            if (!check) {
                System.out.println(incorrectEntryMessage);
                continue;
            }
            System.out.println("Enter a new title");
            try {
                title = reader.readLine();
            } catch (IOException e) {
                logger.error(textErrorMessage, e);
            }
            taskList.getTask(index - 1).setTitle(title);
            TaskIO.writeText(taskList, new File("TaskList.json"));
            System.out.println("Title changed");
            return 5;
        }
    }

}
