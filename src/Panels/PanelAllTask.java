package Panels;

import Сomponents.Builder;
import Main.Main;
import Main.Task;
import Main.Theme;

import javax.swing.*;
import java.util.Calendar;

public class PanelAllTask extends TasksPanel {

    private static PanelAllTask panelAllTask;

    public PanelAllTask() {

        modeAllTask = true;
        JLabel title = Builder.createJLabel("Все задачи", 340, 22, 20, 70, Theme.color4);
        panel.add(title);

        Main.user.sortTasks();

        for(int i = 0; i < Main.user.taskList.size(); i++) {
            Task task = Main.user.taskList.get(i);
            Calendar taskDate = task.getDate();
            String taskDescription = taskDate.get(5) + "." + (taskDate.get(2) + 1) + "." + taskDate.get(1) + ": " + task.getDescription();
            listModel.addElement(new JCheckBox(taskDescription, task.isDone()));
        }

        panel.add(title);
        panel.add(image);

    }

    public static JPanel getPanel() {
        panelAllTask = new PanelAllTask();
        return panelAllTask.panel;
    }

}
