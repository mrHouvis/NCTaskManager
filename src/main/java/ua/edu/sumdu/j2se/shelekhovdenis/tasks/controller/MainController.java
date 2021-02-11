package ua.edu.sumdu.j2se.shelekhovdenis.tasks.controller;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.view.*;

import java.util.ArrayList;
import java.util.List;

/**
 * controller to launch the startup screen
 */
public class MainController extends Controller{

    private AbstractTaskList taskList;
    private View mainView;
    private List<Controller> controllers = new ArrayList<>();

    /**
     * a constructor that contains an array of related constructors for the views to work
     * @param taskList - abstract task list class
     * @param mainView - the view with which the controller works
     */
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


    /**
     * method that launches the view required by the user
     * @param taskList - abstract task list class
     * @return "0" - end of program
     */
    @Override
    public int process(AbstractTaskList taskList) {
        int action = mainView.print(taskList);
        while(action != 0){
            for(Controller controller: controllers){
                if(controller.canProcess(action)){
                    action = controller.process(this.taskList);
                }
            }if(action == 6){
                action = mainView.print(taskList);
            }
        }
        return 0;
    }
}
