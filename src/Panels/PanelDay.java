package Panels;

import Сomponents.Builder;
import Main.Main;
import Main.Task;
import Main.Theme;

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PanelDay extends TasksPanel {

    private static PanelDay panelDay;

    private String[] month = {"янв","февр","март", "апр", "май", "июнь", "июль", "авг", "cент", "окт", "нояб", "дек"};
    private String[] dayOfWeek = {"cб", "вс", "пн", "вт", "ср", "чт", "пт"};

    public PanelDay(GregorianCalendar date) {

        modeAllTask = false;
        dateOpened = date;

        String formatDate = "" + dateOpened.get(5) + "." + month[dateOpened.get(2)] + "." + dateOpened.get(1) + "," + dayOfWeek[dateOpened.get(7) % 7];
        JLabel title = Builder.createJLabel(formatDate, 340, 22, 20, 70, Theme.color4);

        Calendar boxDate = dateOpened;
        for(int i = 0; i < Main.user.taskList.size(); i++) {
            Task check = Main.user.taskList.get(i);
            if(check.getDate().get(1) == boxDate.get(1) && check.getDate().get(2) == boxDate.get(2) && check.getDate().get(5) == boxDate.get(5)) {
                this.listModel.addElement(new JCheckBox(check.getDescription(), check.isDone()));
            }
        }

        panel.add(title);
        panel.add(image);
    }

    public static JPanel getPanel(GregorianCalendar date) {
        panelDay = new PanelDay(date);
        return panelDay.panel;
    }

}
