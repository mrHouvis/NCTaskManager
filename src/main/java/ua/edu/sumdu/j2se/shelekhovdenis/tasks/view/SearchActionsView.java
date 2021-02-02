package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;

import java.io.IOException;

/**
 * outputs options to the console, reads the user's command
 */
public class SearchActionsView extends Constants implements View{

    @Override
    public int print(AbstractTaskList taskList) {

        index = 0;
        check = false;

        while(!check) {
            System.out.println(chooseActionMessage);
            System.out.println("1.- Show all tasks;");
            System.out.println("2.- Show active tasks;");
            System.out.println("3.- Show tasks in between;");
            System.out.println("4.- Return;");
            try {
                index = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e){
                System.out.println(incorrectEntryMessage);
                continue;
            } catch (IOException e) {
                logger.error(textErrorMessage, e);
            }
            for (int i = 1; i < 5; i++) {
                if (index == i) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                System.out.println(incorrectEntryMessage);
            }
        }
        return index;
    }

}
