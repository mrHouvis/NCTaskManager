package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ModifyTaskView implements  View{

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int print(AbstractTaskList taskList) {
        int number = 0;

        for( ; ; ) {
            boolean check = false;
            System.out.println("Choose an action number from the options below.");
            System.out.println("1.- Change activity state task;");
            System.out.println("2.- Modify title;");
            System.out.println("3.- Modify time;");
            System.out.println("4.- Return;");
            try {
                number = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e){
                System.out.println("Incorrect entry, try again!");
                continue;
            } catch (IOException e) {
                logger.error("An error occurred while typing(ModifyTaskView)", e);
            }
            for (int i = 1; i < 5; i++) {
                if (number == i)
                    check = true;
            }
            if (!check) {
                System.out.println("Incorrect entry, try again!");
            } else {
                break;
            }
        }
        return number;
    }

}
