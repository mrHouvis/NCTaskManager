package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.controller.CommonController;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.TaskIO;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;


public class ModifyTimeView implements View{

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int print(AbstractTaskList taskList) {
        int index = 0;
        LocalDate localDate;
        LocalTime localTime;
        LocalDateTime time;
        LocalDateTime start;
        LocalDateTime end;
        int interval;
        boolean repeated = false;
        CommonController showAllTaskController = new CommonController(new ShowAllTaskView(), 4);


        showAllTaskController.process(taskList);
        for( ; ; ) {
            String checked;
            boolean check = false;
            System.out.println("Select the task to be change time (by index)");
            try {
                index = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e){
              System.out.println("Incorrect entry, try again!");
              continue;
            } catch (IOException e) {
                logger.error("An error occurred while typing(ModifyTimeView 1)", e);
            }
            for (int i = 1; i <= taskList.size(); i++) {
                if (index == i)
                    check = true;
            }
            if (!check) {
                System.out.println("Incorrect entry, try again!");
                continue;
            }
            System.out.println("The task must be repeated?(true/false)");
            try {
                checked = reader.readLine();
                if(!checked.equals("true") && !checked.equals("false")) {
                    System.out.println("Incorrect entry, try again!");
                    continue;
                }
                repeated = Boolean.parseBoolean(checked);
            } catch (IOException e) {
                logger.error("An error occurred while typing(ModifyTimeView 2)", e);
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
                    System.out.println("Incorrect entry, try again!");
                } catch (IOException e) {
                    logger.error("An error occurred while typing(ModifyTimeView 3)", e);
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
                    System.out.println("Incorrect entry, try again!");
                } catch (IOException e) {
                    logger.error("An error occurred while typing(ModifyTimeView 4)", e);
                }
            }
        }
    }

}
