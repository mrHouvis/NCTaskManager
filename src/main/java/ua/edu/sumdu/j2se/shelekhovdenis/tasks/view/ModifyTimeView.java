package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.TaskIO;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * allows you to change the number of repetitions and the task execution time
 */
public class ModifyTimeView extends Constants implements View{

    @Override
    public int print(AbstractTaskList taskList) {

        index = 0;
        check = false;
        showAllTaskController.process(taskList);

        for( ; ; ) {
            String checked;
            System.out.println(selectMessage);
            try {
                index = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e){
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
            System.out.println(repeatedMessage);
            try {
                checked = reader.readLine();
                if(!checktrue.equalsIgnoreCase(checked) && !checkfalse.equalsIgnoreCase(checked)) {
                    System.out.println(incorrectEntryMessage);
                    continue;
                }
                repeated = Boolean.parseBoolean(checked);
            } catch (IOException e) {
                logger.error(textErrorMessage, e);
            }
            if (!repeated) {
                try {
                    System.out.println("Enter a new date");
                    localDate = LocalDate.parse(reader.readLine());
                    System.out.println("Enter a new time");
                    localTime = LocalTime.parse(reader.readLine());
                    time = LocalDateTime.of(localDate, localTime);
                    taskList.getTask(index - 1).setTime(time);
                    TaskIO.writeText(taskList, new File("TaskList.json"));
                    System.out.println("Time changed");
                    return 5;
                } catch (DateTimeParseException e){
                    System.out.println(incorrectEntryMessage);
                } catch (IOException e) {
                    logger.error(textErrorMessage, e);
                }
            } else {
                try {
                    System.out.println("Enter a new start date");
                    localDate = LocalDate.parse(reader.readLine());
                    System.out.println("Enter a new start time");
                    localTime = LocalTime.parse(reader.readLine());
                    start = LocalDateTime.of(localDate, localTime);
                    System.out.println("Enter a new end date");
                    localDate = LocalDate.parse(reader.readLine());
                    System.out.println("Enter a new end time");
                    localTime = LocalTime.parse(reader.readLine());
                    end = LocalDateTime.of(localDate, localTime);
                    System.out.println("Enter a new interval");
                    interval = Integer.parseInt(reader.readLine());
                    taskList.getTask(index - 1).setTime(start, end, interval);
                    TaskIO.writeText(taskList, new File("TaskList.json"));
                    System.out.println("Time changed");
                    return 5;
                } catch (DateTimeParseException e){
                    System.out.println(incorrectEntryMessage);
                } catch (IOException e) {
                    logger.error(textErrorMessage, e);
                }
            }
        }
    }

}
