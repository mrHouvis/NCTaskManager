package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.LinkedTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class ShowTaskInBetweenView implements View{

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int print(AbstractTaskList taskList) {
        int number = 1;
        LocalDate localDate;
        LocalTime localTime;
        LocalDateTime from;
        LocalDateTime to;
        AbstractTaskList incomingTaskList = new LinkedTaskList();
        for( ; ; ) {
            try {
                System.out.println("Enter the start date of the interval");
                localDate = LocalDate.parse(reader.readLine());
                System.out.println("Enter the start time of the interval");
                localTime = LocalTime.parse(reader.readLine());
                from = LocalDateTime.of(localDate, localTime);
                System.out.println("Enter the end date of the interval");
                localDate = LocalDate.parse(reader.readLine());
                System.out.println("Enter the end time of the interval");
                localTime = LocalTime.parse(reader.readLine());
                to = LocalDateTime.of(localDate, localTime);
                incomingTaskList = taskList.incoming(from, to);
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect entry, try again!");
                continue;
            } catch (IOException e) {
                logger.error("An error occurred while typing(ShowTaskInBetweenView)", e);
            }
            for (Task task : incomingTaskList) {
                System.out.println(number + ": " + task.toString());
                number++;
            }
            return 5;
        }
    }
}
