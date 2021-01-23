package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.Task;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.TaskIO;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class CreateNewTaskView implements View{

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int print(AbstractTaskList taskList) {
        try {
            taskList.add(createNewTask());
            TaskIO.writeText(taskList, new File("TaskList.json"));
        } catch (IOException e){
            e.printStackTrace();
        }
        return 6;
    }

    private Task createNewTask() throws IOException{
        boolean repeated;
        String title;
        LocalDate localDate;
        LocalTime localTime;
        LocalDateTime time;
        LocalDateTime start;
        LocalDateTime end;
        int interval;
        Task task;

        for( ; ; ) {
            String checked;
            System.out.println("Its a repeated task?(true/false)");
            checked = reader.readLine();
            if(!checked.equals("true") && !checked.equals("false")) {
                System.out.println("Incorrect entry, try again!");
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
                    System.out.println("Incorrect entry, try again!");
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
                    System.out.println("Incorrect entry, try again!");
                }
            }
        }
    }
}
