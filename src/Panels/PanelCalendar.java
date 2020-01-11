package Panels;

import Сomponents.JCalendar;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class PanelCalendar {

    private static PanelCalendar panelCalendar;
    private JDialog dialogChooseDay;

    public PanelCalendar() {

        dialogChooseDay = new JDialog();
        dialogChooseDay.setSize(290, 302);
        dialogChooseDay.setUndecorated(true);

        JCalendar calendar = new JCalendar();
        calendar.setSize(290, 295);
        calendar.setLocation(25, 10);
        dialogChooseDay.add(calendar);

        dialogChooseDay.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                dialogChooseDay.dispose();
                PanelDay.dialog = null;
            }
        });

    }

    public static JDialog getPanelCalendar() {
        panelCalendar = new PanelCalendar();
        return panelCalendar.dialogChooseDay;
    }

}
