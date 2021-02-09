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
public class CreateNewTaskView extends Constants implements View{

    /**
     * adds the created task to the list
     * @param taskList - abstract task list class
     * @return "6" - launching the main view
     */
    @Override
    public int print(AbstractTaskList taskList) {
        try {
            taskList.add(createNewTask());
            TaskIO.writeText(taskList, new File("TaskList.json"));
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
            if(!checktrue.equalsIgnoreCase(checked) && !checkfalse.equalsIgnoreCase(checked)) {
                System.out.println(INCORRECT_ENTRY_MESSAGE);
                continue;
            }
            repeated = Boolean.parseBoolean(checked);
            if (!repeated) {
                System.out.println("Write title");
                title = reader.readLine();
                try {
                    System.out.println("Write date(format yyyy-mm-dd)");
                    localDate = LocalDate.parse(reader.readLine());
                    System.out.println("Write time(format hh:mm:ss or hh:mm)");
                    localTime = LocalTime.parse(reader.readLine());
                    time = LocalDateTime.of(localDate, localTime);
                    task = new Task(title, time);
                    System.out.println(task.toString());
                    return task;
                } catch (DateTimeParseException e) {
                    System.out.println(INCORRECT_ENTRY_MESSAGE);
                }
            } else {
                System.out.println("Write title");
                title = reader.readLine();
                try {
                    System.out.println("Write start date(format yyyy-mm-dd)");
                    localDate = LocalDate.parse(reader.readLine());
                    System.out.println("Write start time(format hh:mm:ss or hh:mm)");
                    localTime = LocalTime.parse(reader.readLine());
                    start = LocalDateTime.of(localDate, localTime);
                    System.out.println("Write end date(format yyyy-mm-dd)");
                    localDate = LocalDate.parse(reader.readLine());
                    System.out.println("Write end time(format hh:mm:ss or hh:mm)");
                    localTime = LocalTime.parse(reader.readLine());
                    end = LocalDateTime.of(localDate, localTime);
                    System.out.println("Write repetition interval(in seconds)");
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
