package Panels;

import Main.*;
import Сomponents.Builder;
import Сomponents.JCalendar;
import Сomponents.JCheckBoxList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

abstract public class TasksPanel {

    public static JPanel panel;
    public static GregorianCalendar dateOpened;

    private static JPanel subPanel;
    private static JPanel panelRedact;

    protected static JTextField description;
    public static JTextField dateField;
    public static JDialog dialog;
    public static boolean modeAllTask;

    protected Box box;
    protected JButton add, delete, redact;
    protected JLabel image;
    protected DefaultListModel listModel;

    public TasksPanel() {

        panel = Builder.createPanel(350, 500, 0, 0);

        JMenu menu = Builder.createAnimatedMenu(50, 40, 10, 10, "points");

        JMenuItem open = Builder.createJMenuItem("Перейти", "open");
        JMenuItem settings = Builder.createJMenuItem("Настройки", "settings");
        JMenuItem exit = Builder.createJMenuItem("Выход", "exit");

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        menuBar.setSize(50,40);
        menuBar.setBorderPainted(false);
        menuBar.setOpaque(false);
        menuBar.setLocation(10,10);

        class MenuButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String name = ((JMenuItem)e.getSource()).getName();

                switch(name) {
                    case "open": {
                        if(dialog == null) {
                            JCalendar.monthMode = true;

                            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

                            int xDialog = Main.window.getX() + 90;
                            int yDialog = Main.window.getY() + 80;

                            dialog = PanelCalendar.getPanelCalendar();

                            if((xDialog + dialog.getWidth()) > dim.width) {
                                xDialog -= dialog.getWidth();
                            }
                            if((yDialog + dialog.getHeight()) > dim.height) {
                                yDialog -= dialog.getHeight();
                            }
                            dialog.setLocation(xDialog, yDialog);
                            dialog.setVisible(true);
                        }
                    } break;
                    case "settings": {
                        Main.window.remove(Main.panel);
                        Main.panel = PanelSettings.getPanelSettings();
                        Builder.repaint(Main.panel);
                    } break;
                    case "exit": {

                        Main.closeProgram();

                    } break;
                }

            }
        }

        MenuButtonListener menuListener = new MenuButtonListener();

        exit.addActionListener(menuListener);
        settings.addActionListener(menuListener);
        open.addActionListener(menuListener);

        menu.add(open);
        menu.add(settings);
        menu.add(exit);

        box = Box.createVerticalBox();
        box.setSize(panel.getWidth() - 40, 300);
        box.setLocation(20, 100);

        listModel = new DefaultListModel();
        JCheckBoxList listEvent = new JCheckBoxList(listModel);
        listEvent.setBounds(20,100,panel.getWidth() - 40, 300);

        box.add(listEvent);

        JScrollPane scrollPane = new JScrollPane(listEvent);
        box.add(scrollPane);

        add = Builder.createButtonWithPic("add", 40, 40, 20, 420, new ImageIcon("Images/add.png"));

        delete = Builder.createButtonWithPic("delete", 40, 40, 80, 420, new ImageIcon("Images/delete.png"));

        redact = Builder.createButtonWithPic("redact", 40, 40, 140, 420, new ImageIcon("Images/redact.png"));

        image = new JLabel (Theme.image1);
        image.setLocation(0,0);
        image.setSize(350, 500);

        class ButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String name = ((JButton)e.getSource()).getName();

                switch(name) {
                    case "add": {
                        if (box.getHeight() == 300) {
                            box.setSize(box.getWidth(), box.getHeight() - 150);
                            add.setLocation(add.getX(), add.getY() - 150);
                            delete.setLocation(delete.getX(), delete.getY() - 150);
                            redact.setLocation(redact.getX(), redact.getY() - 150);
                            panel.remove(image);
                            PanelAdd panelAdd = new PanelAdd(new GregorianCalendar());
                            TasksPanel.subPanel = panelAdd.panel;
                            panel.add(TasksPanel.subPanel);
                            panel.add(image);
                            Main.window.revalidate();
                            Main.window.repaint();
                        }
                    } break;
                    case "delete":{
                        int deleteIndex = listEvent.getSelectedIndex();
                        if(deleteIndex == -1) {
                            break;
                        }

                        String deleteValue = listEvent.getSelectedValue().getText();

                        listModel.remove(deleteIndex);
                        if(modeAllTask) {
                            Main.user.taskList.remove(deleteIndex);
                        } else {
                            deleteIndex = TasksPanel.FindIndex(deleteValue, dateOpened);
                            if(deleteIndex != -1) {
                                Main.user.taskList.remove(deleteIndex);
                            }
                        }
                    } break;
                    case "redact":{
                        if (box.getHeight() == 300) {
                            int redactIndex = listEvent.getSelectedIndex();
                            if(redactIndex == -1) {
                                break;
                            }

                            String valueRedact;
                            if(modeAllTask) {
                                Task task = Main.user.taskList.get(redactIndex);
                                dateOpened = task.getDate();
                                valueRedact = task.getDescription();
                            } else {
                                valueRedact = listEvent.getSelectedValue().getText();
                            }

                            box.setSize(box.getWidth(), box.getHeight() - 150);
                            add.setLocation(add.getX(), add.getY() - 150);
                            delete.setLocation(delete.getX(), delete.getY() - 150);
                            redact.setLocation(redact.getX(), redact.getY() - 150);
                            panel.remove(image);
                            PanelRedact panelRedact = new PanelRedact(dateOpened, valueRedact);
                            TasksPanel.panelRedact = panelRedact.panel;
                            panel.add(TasksPanel.panelRedact);
                            panel.add(image);
                            Main.window.revalidate();
                            Main.window.repaint();
                        }
                    } break;
                }
            }
        }

        ButtonListener listener = new ButtonListener();

        delete.addActionListener(listener );
        add.addActionListener(listener);
        redact.addActionListener(listener);

        panel.add(menuBar);
        panel.add(box);
        panel.add(add);
        panel.add(delete);
        panel.add(redact);

    }

    public static int FindIndex(String value, Calendar date) {

        for(int i = 0; i < Main.user.taskList.size(); i++) {
            Task task = Main.user.taskList.get(i);

            if(task.getDescription().compareTo(value) == 0  && task.getDate().get(1) == date.get(1)
                    && task.getDate().get(2) == date.get(2)
                    && task.getDate().get(5) == date.get(5)) {
                return i;
            }

        }
        return -1;
    }

    private class PanelAdd {

        public JPanel panel;

        public PanelAdd(GregorianCalendar date) {

            panel = Builder.createPanel(310, 150, 20, 330);
            panel.setBackground(Color.white);
            panel.setBorder(BorderFactory.createLineBorder(Theme.color1, 2, true));

            JLabel field1 = Builder.createJLabel("Событие", 195 ,20, 15, 20, Theme.color4);
            panel.add(field1);

            description = Builder.createTextField(205, 30, 15, 40, Theme.color4);
            panel.add(description);

            JLabel field2 = Builder.createJLabel("Дата проведения", 195, 20,15, 80, Theme.color4);
            panel.add(field2);

            dateField =  Builder.createTextField(165, 30, 15, 100, Theme.color4);
            panel.add(dateField);

            JButton chooseDate = Builder.createButtonWithPic("date",30, 30, 190, 100, new ImageIcon("Images/calendar.png"));
            panel.add(chooseDate);

            JButton back = Builder.createButtonWithPic("back",40, 40, 250, 30, new ImageIcon("Images/close.png"));
            panel.add(back);

            JButton addEvent = Builder.createButtonWithPic("add", 40, 40, 250, 80, new ImageIcon("Images/save.png"));
            panel.add(addEvent);

            class ButtonListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                    String name = ((JButton)e.getSource()).getName();

                    switch(name) {
                        case "add" : {
                            addTask(date);
                        } break;
                        case "back" : {
                            Main.window.remove(Main.panel);
                            if(modeAllTask) {
                                Main.panel = PanelAllTask.getPanel();
                            } else {
                                Main.panel = PanelDay.getPanel(dateOpened);
                            }
                            Main.window.getContentPane().add(Main.panel);
                            Main.window.revalidate();
                            Main.window.repaint();
                        } break;
                        case "date" : {
                            if(dialog == null) {
                                JCalendar.monthMode = false;

                                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

                                int xDialog = Main.window.getX() + 225;
                                int yDialog = Main.window.getY() + 445;

                                dialog = PanelCalendar.getPanelCalendar();

                                if((xDialog + dialog.getWidth()) > dim.width) {
                                    xDialog -= dialog.getWidth();
                                }
                                if((yDialog + dialog.getHeight()) > dim.height) {
                                    yDialog -= dialog.getHeight();
                                }
                                dialog.setLocation(xDialog, yDialog);
                                dialog.setVisible(true);
                            }
                        } break;
                    }
                }
            }

            ButtonListener Listener = new ButtonListener();
            back.addActionListener(Listener);
            addEvent.addActionListener(Listener);
            chooseDate.addActionListener(Listener);

        }

        public void addTask(GregorianCalendar dateForDay) {

            String newDescription = description.getText();
            String newDate = dateField.getText();

            String resultCheckDate = Information.CheckInputDate(newDate);
            if(resultCheckDate.compareTo("") != 0) {
                Information.ShowErrorMessage("Ошибка", resultCheckDate);
                return;
            }

            if(newDescription.compareTo("") == 0) {
                Information.ShowErrorMessage("Ошибка", "Описание не может быть пустым");
                return;
            }

            StringTokenizer strbox = new StringTokenizer(newDate, ".");
            String[] date = new String[strbox.countTokens()];
            int i = 0;
            while (strbox.hasMoreTokens()) {
                date[i] = strbox.nextToken();
                i++;
            }
            int year = Integer.parseInt(date[2]), month = Integer.parseInt(date[1]) - 1, day = Integer.parseInt(date[0]);

            Main.user.taskList.add(new Task(new GregorianCalendar(year, month, day), newDescription, false));

            Main.window.remove(Main.panel);
            if(modeAllTask) {
                Main.panel = PanelAllTask.getPanel();
            } else {
                Main.panel = PanelDay.getPanel(dateOpened);
            }
            Main.window.getContentPane().add(Main.panel);
            Main.window.revalidate();
            Main.window.repaint();
        }

    }

    private class PanelRedact {

        public JPanel panel;


        public PanelRedact(GregorianCalendar dateRedact, String valueRedact) {

            panel = Builder.createPanel(310, 150, 20, 330);
            panel.setBackground(Color.white);
            panel.setBorder(BorderFactory.createLineBorder(Theme.color1, 2, true));

            JLabel field1 = Builder.createJLabel("Событие", 195 ,20, 15, 20, Theme.color4);
            panel.add(field1);

            description = Builder.createTextField(205, 30, 15, 40, Theme.color4);
            description.setText(valueRedact);
            panel.add(description);

            JLabel field2 = Builder.createJLabel("Дата проведения", 195, 20,15, 80, Theme.color4) ;
            panel.add(field2);

            String formatDateRedact = "" + dateRedact.get(5) + "." +  (dateRedact.get(2)+1) + "." +  dateRedact.get(1);
            dateField =  Builder.createTextField(165, 30, 15, 100, Theme.color4);
            dateField.setText(formatDateRedact);
            panel.add(dateField);

            JButton chooseDate = Builder.createButtonWithPic( "date",30, 30, 190, 100, new ImageIcon("Images/calendar.png"));
            panel.add(chooseDate);

            JButton back = Builder.createButtonWithPic("back",40, 40, 250, 30, new ImageIcon("Images/close.png"));
            panel.add(back);

            JButton redactEvent = Builder.createButtonWithPic("redact", 40, 40, 250, 80, new ImageIcon("Images/save.png"));
            panel.add(redactEvent);

            class ButtonListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                    String name = ((JButton)e.getSource()).getName();

                    switch(name) {
                        case "redact" : {
                            redactTask(dateRedact, valueRedact);
                        } break;
                        case "back" : {
                            Main.window.remove(Main.panel);
                            if(modeAllTask) {
                                Main.panel = PanelAllTask.getPanel();
                            } else {
                                Main.panel = PanelDay.getPanel(dateOpened);
                            }
                            Main.window.getContentPane().add(Main.panel);
                            Main.window.revalidate();
                            Main.window.repaint();
                            Main.panel.setEnabled(true);
                        } break;
                        case "date" : {
                            if(dialog == null) {
                                JCalendar.monthMode = false;

                                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

                                int xDialog = Main.window.getX() + 225;
                                int yDialog = Main.window.getY() + 445;

                                dialog = PanelCalendar.getPanelCalendar();

                                if((xDialog + dialog.getWidth()) > dim.width) {
                                    xDialog -= dialog.getWidth();
                                }
                                if((yDialog + dialog.getHeight()) > dim.height) {
                                    yDialog -= dialog.getHeight();
                                }
                                dialog.setLocation(xDialog, yDialog);
                                dialog.setVisible(true);
                            }
                        } break;

                    }
                }
            }

            ButtonListener Listener = new ButtonListener();
            back.addActionListener(Listener);
            redactEvent.addActionListener(Listener);
            chooseDate.addActionListener(Listener);

        }

        public void redactTask(Calendar dateRedact, String valueRedact) {
            int redactIndexList = TasksPanel.FindIndex(valueRedact, dateRedact);

            String newDescription = description.getText();
            String newDate = dateField.getText();

            String resultCheckDate = Information.CheckInputDate(newDate);
            if(resultCheckDate.compareTo("") != 0) {
                Information.ShowErrorMessage("Ошиббка", resultCheckDate);
                return;
            }

            if(newDescription.compareTo("") == 0) {
                Information.ShowErrorMessage("Ошиббка", "Описание не может быть пустым");
                return;
            }

            String oldDate = "" + dateRedact.get(5) + "." + (dateRedact.get(2) + 1) + "." + dateRedact.get(1);
            if(newDescription.compareTo(valueRedact) == 0 && newDate.compareTo(oldDate) == 0) {
                Information.ShowErrorMessage("Ошибка", "Старое описание события не может совпадать с новым");
                return;
            }
            StringTokenizer strbox = new StringTokenizer(newDate, ".");
            String[] date = new String[strbox.countTokens()];
            int i = 0;
            while (strbox.hasMoreTokens()) {
                date[i] = strbox.nextToken();
                i++;
            }
            int year = Integer.parseInt(date[2]), month = Integer.parseInt(date[1]) - 1, day = Integer.parseInt(date[0]);

            Main.user.taskList.set(redactIndexList, new Task(new GregorianCalendar(year, month, day), newDescription, false));

            Main.window.remove(Main.panel);
            if(modeAllTask) {
                Main.panel = PanelAllTask.getPanel();
            } else {
                Main.panel = PanelDay.getPanel(dateOpened);
            }
            Main.window.getContentPane().add(Main.panel);
            Main.window.revalidate();
            Main.window.repaint();
            Main.panel.setEnabled(true);
        }

    }

}
