package ua.edu.sumdu.j2se.shelekhovdenis.tasks.view;

import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.shelekhovdenis.tasks.model.Task;

import java.io.IOException;
import java.awt.*;
import java.time.LocalDateTime;

/**
 * daemon class for checking the execution of tasks and displaying messages about their completion
 */
public class CheckEventsDaemonView implements View {

    /**
     * method for converting class daemon
     * @param taskList - abstract task list class
     * @return "0" - end of program
     */
    @Override
    public int print(AbstractTaskList taskList) {
        try{
            System.in.close();
            System.out.close();
        } catch (IOException e) {
            logger.error("Daemonized failed", e);
            return 0;
        }

        doProcessing(taskList);
        return 0;
    }

    /**
     * checks the completion of tasks every minute
     * @param taskList - abstract task list class
     */
    private void doProcessing(AbstractTaskList taskList){
        for( ; ; ) {
            AbstractTaskList activeTaskList;
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                logger.error("InterruptedException", e);
            }
            activeTaskList = taskList.incoming(LocalDateTime.now().minusMinutes(1), LocalDateTime.now());
            for(Task task : activeTaskList){
                callMessage(task);
            }
        }
    }

    /**
     * displays a message about the completion of the task
     * @param task - completed task
     */
    private void callMessage(Task task){
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();

            java.awt.Image image = Toolkit.getDefaultToolkit().getImage("images/image.gif");
            TrayIcon trayIcon = new TrayIcon(image);
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                logger.error("Тo notification icon", e);
            }
            trayIcon.displayMessage("An event happened", "It's time for the event: " + task.getTitle(), TrayIcon.MessageType.INFO);
        }
    }
}
