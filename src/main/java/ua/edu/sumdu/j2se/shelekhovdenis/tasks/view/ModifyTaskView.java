package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;

import java.io.IOException;

/**
 * outputs options to the console, reads the user's command
 */
public class ModifyTaskView extends Constants implements  View{

    @Override
    public int print(AbstractTaskList taskList) {

        index = 0;
        check = false;

        while(!check){
            System.out.println(CHOOSE_ACTION_MESSAGE);
            System.out.println("1.- Change activity state task;");
            System.out.println("2.- Modify title;");
            System.out.println("3.- Modify time;");
            System.out.println("4.- Return;");
            try {
                index = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e){
                System.out.println(INCORRECT_ENTRY_MESSAGE);
                continue;
            } catch (IOException e) {
                logger.error(TEXT_ERROR_MESSAGE, e);
            }
            for (int i = 1; i < 5; i++) {
                if (index == i)
                    check = true;
            }
            if (!check) {
                System.out.println(INCORRECT_ENTRY_MESSAGE);
            }
        }
        return index;
    }

}
