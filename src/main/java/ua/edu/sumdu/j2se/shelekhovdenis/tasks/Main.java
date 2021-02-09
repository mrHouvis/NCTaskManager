package ua.edu.sumdu.j2se.shelekhovdenis.tasks;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.controller.MainController;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.LinkedTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.view.Constants;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.view.MainView;

import java.io.File;

/**
 * main class of the program
 */
public class Main implements Constants {

    public static void main (String [] args) {

        MainView mainView = new MainView();
        AbstractTaskList taskList = new LinkedTaskList();
        TaskIO.readText(taskList, new File(PATHNAME_FILE));
        MainController mainController = new MainController(taskList, mainView);
        mainController.process(taskList);
    }

}
