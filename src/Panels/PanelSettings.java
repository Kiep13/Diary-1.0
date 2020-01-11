package Panels;

import Main.*;
import Сomponents.Builder;
import Сomponents.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PanelSettings {

    private static PanelSettings panelSettings;
    private JPanel panel;

    private JPanel subPanel;

    private JButton set1;
    private JButton set2;

    private JLabel image;

    public PanelSettings() {

        panel = Builder.createPanel(340, 410, 0, 0);

        JMenu menu = Builder.createAnimatedMenu(50, 40, 10, 10, "points");

        JMenuItem open = Builder.createJMenuItem("Перейти", "open");
        JMenuItem exit = Builder.createJMenuItem("Выход", "exit");
        exit.setName("exit");

        menu.add(open);
        menu.add(exit);

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
                        if(PanelDay.dialog == null) {
                            JCalendar.monthMode = true;

                            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

                            int xDialog = Main.window.getX() + 90;
                            int yDialog = Main.window.getY() + 80;

                            PanelDay.dialog = PanelCalendar.getPanelCalendar();

                            if((xDialog + PanelDay.dialog.getWidth()) > dim.width) {
                                xDialog -= PanelDay.dialog.getWidth();
                            }
                            if((yDialog + PanelDay.dialog.getHeight()) > dim.height) {
                                yDialog -= PanelDay.dialog.getHeight();
                            }
                            PanelDay.dialog.setLocation(xDialog, yDialog);
                            PanelDay.dialog.setVisible(true);
                        }
                    } break;
                    case "exit": {

                        Main.closeProgram();

                    } break;
                }

            }
        }

        MenuButtonListener menuListener = new MenuButtonListener();

        exit.addActionListener(menuListener);
        open.addActionListener(menuListener);

        set1 = Builder.createLimpidJButton("<html>Автоматическая очистка прошедших событий</html>", "autoClear", panel.getWidth() - 40, 50, 20, 70);

        set2 = Builder.createLimpidJButton("Цветовая тема", "colorTheme",panel.getWidth() - 40, 30, 20, 140);

        image = new JLabel (Theme.image3);
        image.setLocation(0,0);
        image.setSize(350, 500);

        class SettingsListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String name = ((JButton)e.getSource()).getName();

                switch(name) {
                    case "autoClear": {
                        if(set2.getY() == 140) {
                            PanelSetAutoClear panelSetAutoClear = new PanelSetAutoClear();
                            subPanel = panelSetAutoClear.panel;
                            panel.remove(image);
                            set2.setLocation(set2.getX(), set2.getY() + 60);
                            panel.add(subPanel);
                            panel.add(image);
                            Main.window.revalidate();
                            Main.window.repaint();
                        }
                    } break;
                    case "colorTheme": {
                        if(set2.getY() == 140) {
                            PanelSetTheme panelSetTheme = new PanelSetTheme();
                            subPanel = panelSetTheme.panel;
                            panel.remove(image);
                            panel.add(subPanel);
                            panel.add(image);
                            Main.window.revalidate();
                            Main.window.repaint();
                        }
                    } break;
                }

            }
        }

        SettingsListener setListener = new SettingsListener();

        set1.addActionListener(setListener);
        set2.addActionListener(setListener);

        panel.add(set1);
        panel.add(set2);

        panel.add(menuBar);
        panel.add(image);

    }

    public static JPanel getPanelSettings() {
        panelSettings = new PanelSettings();
        return panelSettings.panel;
    }

    private class PanelSetTheme {

        public JPanel panel;

        public PanelSetTheme() {

            panel = Builder.createPanel(Main.panel.getWidth() - 40, 110, 20, 180);
            panel.setBackground(Color.white);
            panel.setBorder(BorderFactory.createLineBorder(Theme.color1, 2, true));

            JPanel panelRadio = new JPanel(new GridLayout(0, 1, 0, 5));
            String[] names = { "Стандарт", "Пудра", "Морской бриз", "Кошмар Джессики"};
            ButtonGroup buttonGroup = new ButtonGroup();

            class RadioButtonListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setColor(((JRadioButton)e.getSource()).getText());
                }
            }

            for (int i = 0; i < names.length; i++) {
                JRadioButton radio = new JRadioButton(names[i]);
                radio.setBackground(Color.white);
                radio.setBorderPainted(false);
                radio.setFocusPainted(false);
                radio.addActionListener(new RadioButtonListener());
                if(Main.user.nameColorTheme.compareTo(names[i]) == 0) {
                    radio.setSelected(true);
                }
                panelRadio.add(radio);
                buttonGroup.add(radio);
            }
            panelRadio.setSize(150, 90);
            panelRadio.setLocation(10, 10);
            panelRadio.setBackground(Color.white);

            panel.add(panelRadio);

            JButton back = Builder.createButtonWithPic("back",40, 40, panel.getWidth() - 50, 10, new ImageIcon("Images/close.png"));
            panel.add(back);

            class ButtonListener implements ActionListener {
                public void actionPerformed(ActionEvent e) {
                    String name = ((JButton)e.getSource()).getName();

                    switch(name) {
                        case "back" : {
                            Main.panel.remove(subPanel);
                            Main.window.revalidate();
                            Main.window.repaint();
                        } break;
                    }
                }
            }

            ButtonListener Listener = new ButtonListener();
            back.addActionListener(Listener);
        }

        private void setColor(String name) {

            if(name.compareTo(Main.user.nameColorTheme) == 0) {
                return;
            }

            Main.user.nameColorTheme = name;

            Theme.setTheme(Main.user.nameColorTheme);

            Main.window.remove(Main.panel);
            Main.panel = PanelSettings.getPanelSettings();

            Main.window.add(Main.panel);
            Main.window.revalidate();
            Main.window.repaint();

        }

    }

    private class PanelSetAutoClear {

        public JPanel panel;

        public PanelSetAutoClear() {

            panel = Builder.createPanel(Main.panel.getWidth() - 40, 60, 20, 130);
            panel.setBackground(Color.white);
            panel.setBorder(BorderFactory.createLineBorder(Theme.color1, 2, true));

            JCheckBox isAutoClear = new JCheckBox("Очищать");
            isAutoClear.setSize(150, 15);
            isAutoClear.setLocation(10,22);
            isAutoClear.setBorderPainted(false);
            isAutoClear.setFocusPainted(false);
            isAutoClear.setFont(Theme.font3);
            isAutoClear.setBackground(Color.white);
            isAutoClear.setSelected(Main.user.autoClear);

            panel.add(isAutoClear);

            JButton save = Builder.createButtonWithPic("save", 40, 40, panel.getWidth() - 50, 10, new ImageIcon("Images/save.png"));
            panel.add(save);

            save.addActionListener(e -> setAutoClear(isAutoClear));

        }

        public void setAutoClear(JCheckBox checkBox) {

            if(checkBox.isSelected() == Main.user.autoClear) {
                Main.panel.remove(subPanel);
                set2.setLocation(set2.getX(), set2.getY() - 60);
                Main.window.revalidate();
                Main.window.repaint();
                return;
            }

            Main.user.autoClear = checkBox.isSelected();

            Information.ShowInformationMessage("Информация", "Статус автоматической очистки успешно измененен!");

            if(Main.user.autoClear) {

                Calendar today = new GregorianCalendar();
                int year = today.get(1), month = today.get(2), day = today.get(5);

                for(int i = 0; i < Main.user.taskList.size(); i++) {
                    Calendar dayTask = Main.user.taskList.get(i).getDate();

                    if(dayTask.get(1) < year) {
                        Main.user.taskList.remove(i);
                        i--;
                    } else if(dayTask.get(1) == year && dayTask.get(2) < month) {
                        Main.user.taskList.remove(i);
                        i--;
                    } else if(dayTask.get(1) == year && dayTask.get(2) == month && dayTask.get(5) < day) {
                        Main.user.taskList.remove(i);
                        i--;
                    }

                }

            }

            Main.panel.remove(subPanel);
            set2.setLocation(set2.getX(), set2.getY() - 60);
            Main.window.revalidate();
            Main.window.repaint();

        }

    }

}
