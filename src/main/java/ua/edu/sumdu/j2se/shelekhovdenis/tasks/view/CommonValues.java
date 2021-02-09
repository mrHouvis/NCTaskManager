package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.controller.CommonController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * class for storing constants
 */
public class CommonValues {

    protected BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    protected CommonController showAllTaskController = new CommonController(new ShowAllTaskView(), 4);
    protected int index = 0;
    protected boolean check = false;

    protected boolean repeated;
    protected String title;
    protected LocalDate localDate;
    protected LocalTime localTime;
    protected LocalDateTime time;
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected int interval;

}
