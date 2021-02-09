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
public class Constants {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    CommonController showAllTaskController = new CommonController(new ShowAllTaskView(), 4);
    int index = 0;
    boolean check = false;
    String checktrue = "true";
    String checkfalse = "false";

    boolean repeated;
    String title;
    LocalDate localDate;
    LocalTime localTime;
    LocalDateTime time;
    LocalDateTime start;
    LocalDateTime end;
    int interval;

    public static final String SELECT_MESSAGE = "Select the task (by index)";
    public static final String REPEATED_MESSAGE = "Its a repeated task?(true/false)";
    public static final String INCORRECT_ENTRY_MESSAGE = "Incorrect entry, try again!";
    public static final String TEXT_ERROR_MESSAGE = "An error occurred while typing";
    public static final String CHOOSE_ACTION_MESSAGE = "Choose an action number from the options below.";
}
