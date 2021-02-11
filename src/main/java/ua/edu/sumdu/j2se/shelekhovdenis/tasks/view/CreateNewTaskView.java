package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.Task;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.TaskIO;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * creates a task and adds it to the list
 */
public class CreateNewTaskView extends CommonValues implements View, Constants{

    /**
     * adds the created task to the list
     * @param taskList - abstract task list class
     * @return "6" - launching the main view
     */
    @Override
    public int print(AbstractTaskList taskList) {
        try {
            taskList.add(createNewTask());
            TaskIO.writeText(taskList, new File(PATHNAME_FILE));
        } catch (IOException e){
            logger.error(TEXT_ERROR_MESSAGE, e);
        }
        return 6;
    }

    /**
     * creates a new task for the selected parameters
     * @return created task
     * @throws IOException
     */
    private Task createNewTask() throws IOException{
        Task task;

        for( ; ; ) {
            String checked;
            System.out.println(REPEATED_MESSAGE);
            checked = reader.readLine();
            if(!CHECK_TRUE.equalsIgnoreCase(checked) && !CHECK_FALSE.equalsIgnoreCase(checked)) {
                System.out.println(INCORRECT_ENTRY_MESSAGE);
                continue;
            }
            repeated = Boolean.parseBoolean(checked);
            if (!repeated) {
                System.out.println(WRITE_TITLE_MESSAGE);
                title = reader.readLine();
                try {
                    System.out.println(WRITE_DATE_MESSAGE);
                    localDate = LocalDate.parse(reader.readLine());
                    System.out.println(WRITE_TIME_MESSAGE);
                    localTime = LocalTime.parse(reader.readLine());
                    time = LocalDateTime.of(localDate, localTime);
                    task = new Task(title, time);
                    System.out.println(task.toString());
                    return task;
                } catch (DateTimeParseException e) {
                    System.out.println(INCORRECT_ENTRY_MESSAGE);
                }
            } else {
                System.out.println(WRITE_TITLE_MESSAGE);
                title = reader.readLine();
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
                    task = new Task(title, start, end, interval);
                    System.out.println(task.toString());
                    return task;
                } catch (DateTimeParseException e) {
                    System.out.println(INCORRECT_ENTRY_MESSAGE);
                }
            }
        }
    }
}
