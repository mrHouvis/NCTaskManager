package ua.edu.sumdu.j2se.shelekhovdenis.tasks.controller;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.view.View;

public class CommonController extends Controller{

    public CommonController(View view, int mainAction){
        super(view, mainAction);
    }

    @Override
    public int process(AbstractTaskList taskList) {
        return view.print(taskList);
    }
}
