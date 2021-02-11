package ua.edu.sumdu.j2se.shelekhovdenis.tasks.controller;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.view.View;

/**
 * the main controller that does the work with the list
 */
public class CommonController extends Controller{

    public CommonController(View view, int mainAction){
        super(view, mainAction);
    }

    /**
     * method of starting work with a view
     * @param taskList - abstract task list class
     * @return next action number
     */
    @Override
    public int process(AbstractTaskList taskList) {
        return view.print(taskList);
    }
}
