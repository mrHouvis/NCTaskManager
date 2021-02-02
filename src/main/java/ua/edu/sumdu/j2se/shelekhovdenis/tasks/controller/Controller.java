package ua.edu.sumdu.j2se.shelekhovdenis.tasks.controller;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.view.View;

/**
 * abstract class for controllers
 */
public abstract class Controller {
    protected View view;
    protected int mainAction;

    /**
     * basic constructor for controllers
     * @param view - the view with which the controller works
     * @param mainAction - parameter to return the action value
     */
    public Controller(View view, int mainAction){
        this.view = view;
        this.mainAction = mainAction;
    }

    /**
     * default constructor
     */
    protected Controller(){}

    /**
     * method for checking the value of the action
     * @param action - parameter to return the action value
     * @return "true" - if action is equal mainAction
     */
    public boolean canProcess(int action){
        return action == mainAction;
    }

    /**
     * method of starting the controller
     * @param taskList - abstract task list class
     * @return next action number
     */
    public abstract int process(AbstractTaskList taskList);
}
