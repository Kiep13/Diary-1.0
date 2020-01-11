package Сomponents;

import Main.Main;
import Main.Theme;
import Panels.PanelDay;
import Panels.TasksPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JCheckBoxList extends JList<JCheckBox> {
    protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);

    public JCheckBoxList() {
        setCellRenderer(new CellRenderer());
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                if (index != -1) {
                    JCheckBox checkbox = (JCheckBox) getModel().getElementAt(index);
                    checkbox.setSelected(!checkbox.isSelected());
                    repaint();

                    if(TasksPanel.modeAllTask) {
                        boolean isDone = Main.user.taskList.get(index).isDone();
                        Main.user.taskList.get(index).setDone(!isDone);
                        return;
                    }

                    int indexInList = TasksPanel.FindIndex(checkbox.getText(), PanelDay.dateOpened);
                    if(indexInList != -1) {
                        boolean isDone = Main.user.taskList.get(indexInList).isDone();
                        Main.user.taskList.get(indexInList).setDone(!isDone);
                    }
                }
            }
        });
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public JCheckBoxList(ListModel<JCheckBox> model){
        this();
        setModel(model);
    }

    protected class CellRenderer implements ListCellRenderer<JCheckBox> {
        public Component getListCellRendererComponent(
                JList<? extends JCheckBox> list, JCheckBox value, int index,
                boolean isSelected, boolean cellHasFocus) {
            JCheckBox checkbox = value;

            checkbox.setBackground(isSelected ? Theme.color5
                    : getBackground());
            checkbox.setForeground(isSelected ? getSelectionForeground()
                    : getForeground());
            checkbox.setEnabled(isEnabled());
            checkbox.setFont(getFont());
            checkbox.setFocusPainted(false);
            checkbox.setBorderPainted(true);
            checkbox.setBorder(isSelected ? UIManager
                    .getBorder("List.focusCellHighlightBorder") : noFocusBorder);
            return checkbox;
        }
    }
}