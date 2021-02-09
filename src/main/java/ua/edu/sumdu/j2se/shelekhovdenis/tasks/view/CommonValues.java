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

}
