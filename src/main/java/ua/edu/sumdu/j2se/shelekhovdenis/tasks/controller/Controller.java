package ua.edu.sumdu.j2se.shelekhovdenis.tasks.controller;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.view.View;

public abstract class Controller {
    protected View view;
    protected int mainAction;

    public Controller(View view, int mainAction){
        this.view = view;
        this.mainAction = mainAction;
    }

    protected Controller(){}

    public boolean canProcess(int action){
        return action == mainAction;
    }

    public abstract int process(AbstractTaskList taskList);
}
