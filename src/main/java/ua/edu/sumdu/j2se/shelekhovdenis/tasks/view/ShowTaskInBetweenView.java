package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.LinkedTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * displays tasks that run in a given interval
 */
public class ShowTaskInBetweenView extends CommonValues implements View, Constants{

    @Override
    public int print(AbstractTaskList taskList) {

        index = 1;
        AbstractTaskList incomingTaskList = new LinkedTaskList();

        for( ; ; ) {
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
                incomingTaskList = taskList.incoming(start, end);
            } catch (DateTimeParseException e) {
                System.out.println(INCORRECT_ENTRY_MESSAGE);
                continue;
            } catch (IOException e) {
                logger.error(TEXT_ERROR_MESSAGE, e);
            }
            for (Task task : incomingTaskList) {
                System.out.println(index + ": " + task.toString());
                index++;
            }
            return 5;
        }
    }
}
