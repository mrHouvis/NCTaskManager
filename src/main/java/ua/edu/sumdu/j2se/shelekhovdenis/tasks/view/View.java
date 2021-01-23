package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;

public interface View {

    Logger logger = Logger.getLogger(View.class);
    int print(AbstractTaskList taskList);
}
