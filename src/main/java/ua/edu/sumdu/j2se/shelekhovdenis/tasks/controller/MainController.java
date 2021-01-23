package ua.edu.sumdu.j2se.shelekhovdenis.tasks.controller;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.view.*;

import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller{

    private AbstractTaskList taskList;
    private View mainView;
    private List<Controller> controllers = new ArrayList<>();

    public MainController(AbstractTaskList taskList, View mainView){
        this.mainView = mainView;
        this.mainAction = 0;
        this.taskList = taskList;

        controllers.add(this);
        controllers.add(new CommonController(new CreateNewTaskView(), 1));
        controllers.add(new ModifyTaskController(new ModifyTaskView(), taskList));
        controllers.add(new CommonController(new DeleteTaskView(), 3));
        controllers.add(new SearchActionsController(new SearchActionsView(), taskList));
        controllers.add(new CommonController(new CheckEventsDaemonView(), 5));
    }


    @Override
    public int process(AbstractTaskList taskList) {
        int action = mainView.print(taskList);
        for( ; ; ){
            for(Controller controller: controllers){
                if(controller.canProcess(action)){
                    action = controller.process(this.taskList);
                }
            }
            if(action == 0){
                break;
            }if(action == 6){
                action = mainView.print(taskList);
            }

        }
        return 0;
    }
}
