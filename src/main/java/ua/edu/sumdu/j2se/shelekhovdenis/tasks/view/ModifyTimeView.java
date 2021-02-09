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
public class ModifyTimeView extends CommonValues implements View, Constants{

    @Override
    public int print(AbstractTaskList taskList) {

        index = 0;
        check = false;
        showAllTaskController.process(taskList);

        for( ; ; ) {
            String checked;
            System.out.println(SELECT_MESSAGE);
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
                continue;
            }
            System.out.println(REPEATED_MESSAGE);
            try {
                checked = reader.readLine();
                if(!checktrue.equalsIgnoreCase(checked) && !checkfalse.equalsIgnoreCase(checked)) {
                    System.out.println(INCORRECT_ENTRY_MESSAGE);
                    continue;
                }
                repeated = Boolean.parseBoolean(checked);
            } catch (IOException e) {
                logger.error(TEXT_ERROR_MESSAGE, e);
            }
            if (!repeated) {
                try {
                    System.out.println(WRITE_DATE_MESSAGE);
                    localDate = LocalDate.parse(reader.readLine());
                    System.out.println(WRITE_TIME_MESSAGE);
                    localTime = LocalTime.parse(reader.readLine());
                    time = LocalDateTime.of(localDate, localTime);
                    taskList.getTask(index - 1).setTime(time);
                    TaskIO.writeText(taskList, new File(PATHNAME_FILE));
                    System.out.println(TIME_CHANGED_MESSAGE);
                    return 5;
                } catch (DateTimeParseException e){
                    System.out.println(INCORRECT_ENTRY_MESSAGE);
                } catch (IOException e) {
                    logger.error(TEXT_ERROR_MESSAGE, e);
                }
            } else {
                try {
                    System.out.println(WRITE_START_DATE_MESSAGE);
                    localDate = LocalDate.parse(reader.readLine());
                    System.out.println(WRITE_START_TIME_MESSAGE);
                    localTime = LocalTime.parse(reader.readLine());
                    start = LocalDateTime.of(localDate, localTime);
                    System.out.println(WRITE_END_DATE_MESSAGE);
                    localDate = LocalDate.parse(reader.readLine());
                    System.out.println(WRITE_END_TIME_MESSAGE);
                    localTime = LocalTime.parse(reader.readLine());
                    end = LocalDateTime.of(localDate, localTime);
                    System.out.println(WRITE_INTERVAL_MESSAGE);
                    interval = Integer.parseInt(reader.readLine());
                    taskList.getTask(index - 1).setTime(start, end, interval);
                    TaskIO.writeText(taskList, new File(PATHNAME_FILE));
                    System.out.println(TIME_CHANGED_MESSAGE);
                    return 5;
                } catch (DateTimeParseException e){
                    System.out.println(INCORRECT_ENTRY_MESSAGE);
                } catch (IOException e) {
                    logger.error(TEXT_ERROR_MESSAGE, e);
                }
            }
        }
    }

}
