package ua.edu.sumdu.j2se.shelekhovdenis.tasks.controller;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.view.*;

import java.util.ArrayList;
import java.util.List;

/**
 * controller for working with actions related to task modification
 */
public class ModifyTaskController extends Controller{

    private AbstractTaskList taskList;
    private View modifyTaskView;
    private List<Controller> controllers = new ArrayList<>();

    /**
     * a constructor that contains an array of related constructors for the views to work
     * @param modifyTaskView - the view with which the controller works
     * @param taskList - abstract task list class
     */
    public ModifyTaskController(View modifyTaskView, AbstractTaskList taskList){
        this.modifyTaskView = modifyTaskView;
        this.mainAction = 2;
        this.taskList = taskList;

        controllers.add(new CommonController(new ActivateTaskView(), 1));
        controllers.add(new CommonController(new ModifyTitleView(), 2));
        controllers.add(new CommonController(new ModifyTimeView(), 3));

    }

    /**
     * method that launches the view required by the user
     * @param taskList - abstract task list class
     * @return "6" - launching the main view
     */
    @Override
    public int process(AbstractTaskList taskList) {
        int action = modifyTaskView.print(taskList);
        while(action != 4){
            for(Controller controller: controllers){
                if(controller.canProcess(action)){
                    action = controller.process(this.taskList);
                }
            }if(action == 5){
                action = modifyTaskView.print(taskList);
            }

        }
        return 6;
    }
}
