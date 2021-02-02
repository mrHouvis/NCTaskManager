package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;

/**
 * interface that is the basis for all views
 */
public interface View {
    Logger logger = Logger.getLogger(View.class);

    /**
     * method for outputting results to the console
     * @param taskList - abstract task list class
     * @return next action number
     */
    int print(AbstractTaskList taskList);
}
