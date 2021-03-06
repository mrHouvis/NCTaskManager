package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.TaskIO;

import java.io.File;
import java.io.IOException;

/**
 * allows you to change the name of the task
 */
public class ModifyTitleView extends CommonValues implements View, Constants{

    @Override
    public int print(AbstractTaskList taskList) {

        index = 0;
        check = false;
        showAllTaskController.process(taskList);

        for( ; ; ){
            System.out.println(SELECT_MESSAGE);
            try {
                index = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
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
                continue;
            }
            System.out.println(WRITE_TITLE_MESSAGE);
            try {
                title = reader.readLine();
            } catch (IOException e) {
                logger.error(TEXT_ERROR_MESSAGE, e);
            }
            taskList.getTask(index - 1).setTitle(title);
            TaskIO.writeText(taskList, new File(PATHNAME_FILE));
            System.out.println(TITLE_CHANGED_MESSAGE);
            return 5;
        }
    }

}
