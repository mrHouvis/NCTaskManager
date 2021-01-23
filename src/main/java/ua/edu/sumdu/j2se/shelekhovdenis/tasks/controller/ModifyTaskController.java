package ua.edu.sumdu.j2se.shelekhovdenis.tasks.controller;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.view.*;

import java.util.ArrayList;
import java.util.List;

public class ModifyTaskController extends Controller{

    private AbstractTaskList taskList;
    private View modifyTaskView;
    private List<Controller> controllers = new ArrayList<>();

    public ModifyTaskController(View modifyTaskView, AbstractTaskList taskList){
        this.modifyTaskView = modifyTaskView;
        this.mainAction = 2;
        this.taskList = taskList;

        controllers.add(new CommonController(new ActivateTaskView(), 1));
        controllers.add(new CommonController(new ModifyTitleView(), 2));
        controllers.add(new CommonController(new ModifyTimeView(), 3));

    }

    @Override
    public int process(AbstractTaskList taskList) {
        int action = modifyTaskView.print(taskList);
        for( ; ; ){
            for(Controller controller: controllers){
                if(controller.canProcess(action)){
                    action = controller.process(this.taskList);
                }
            }
            if(action == 4){
                break;
            }if(action == 5){
                action = modifyTaskView.print(taskList);
            }

        }
        return 6;
    }
}
