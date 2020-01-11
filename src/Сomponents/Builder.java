package Сomponents;

import Main.Main;
import Main.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Builder {

    public static JButton createButton(String text, String name, int width, int height, int x, int y, Color textColor) {
        JButton button = new JButton(text);
        button.setSize(width, height);
        button.setLocation(x,y);
        button.setName(name);

        button.setMargin(new Insets(0,0,0,0));

        button.setBackground(Theme.color5);
        button.setFont(Theme.font2);
        button.setForeground(textColor);
        button.setBorder(BorderFactory.createLineBorder(Theme.color4, 2));
        button.setFocusPainted(false);

        return button;
    }

    public static JButton createButtonWithPic(String name, int width, int height, int x, int y, ImageIcon icon) {
        JButton buttonPic = new JButton();
        buttonPic.setSize(width, height);
        buttonPic.setLocation(x,y);
        buttonPic.setName(name);

        buttonPic.setMargin(new Insets(0,0,0,0));

        buttonPic.setIcon(icon);
        buttonPic.setContentAreaFilled(false);
        buttonPic.setBorder(null);

        return buttonPic;
    }

    public static JButton createAnimatedButton(int width, int height, int x, int y, String iconName) {
        JButton buttonAnimated = new JButton();
        buttonAnimated.setIcon(new ImageIcon("Images/" + iconName + ".png"));
        buttonAnimated.setSize(width, height);
        buttonAnimated.setLocation(x, y);
        buttonAnimated.setOpaque(true);
        buttonAnimated.setContentAreaFilled(false);
        buttonAnimated.setBorderPainted(false);
        buttonAnimated.setFocusPainted( false );

        buttonAnimated.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonAnimated.setIcon(new ImageIcon("Images/" + iconName + "White.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonAnimated.setIcon(new ImageIcon("Images/" + iconName + ".png"));
            }
        });

        return buttonAnimated;
    }

    public static JMenu createAnimatedMenu(int width, int height, int x, int y, String iconName) {
        JMenu buttonAnimated = new JMenu();
        buttonAnimated.setSize(width,height);
        buttonAnimated.setIcon(new ImageIcon("Images/" + iconName + ".png"));
        buttonAnimated.setLocation(x, y);
        buttonAnimated.setBorderPainted(false);
        buttonAnimated.setFocusPainted( false );
        buttonAnimated.setContentAreaFilled(false);

        buttonAnimated.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                buttonAnimated.setIcon(new ImageIcon("Images/" + iconName + "White.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                buttonAnimated.setIcon(new ImageIcon("Images/" + iconName + ".png"));
            }
        });

        return buttonAnimated;
    }

    public static JToggleButton creteAnimatedButton(int width, int height, int x, int y, String iconNameOn) {
        JToggleButton buttonAnimated = new JToggleButton();
        buttonAnimated.setIcon(new ImageIcon("Images/" + iconNameOn + ".png"));
        buttonAnimated.setBorderPainted(false);
        buttonAnimated.setFocusPainted( false );
        buttonAnimated.setContentAreaFilled(false);

        buttonAnimated.setSize(width, height);
        buttonAnimated.setLocation(x, y);
        buttonAnimated.setVisible(true);

        buttonAnimated.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) {

                Icon icon = buttonAnimated.getIcon();
                String nameFixedIcon = icon.toString();
                int indexFirst = nameFixedIcon.indexOf("/");
                int indexSecond = nameFixedIcon.indexOf(".");
                String newNameIcon = nameFixedIcon.substring( indexFirst+1, indexSecond );
                buttonAnimated.setIcon(new ImageIcon("Images/" + newNameIcon + "White.png"));
            }

            public void mouseExited(MouseEvent e) {
                Icon icon = buttonAnimated.getIcon();
                String nameFixedIcon = icon.toString();
                int indexFirst = nameFixedIcon.indexOf("/");
                int indexSecond = nameFixedIcon.indexOf("White");
                if(indexSecond == -1) {
                    indexSecond = nameFixedIcon.indexOf(".");
                }
                String newNameIcon = nameFixedIcon.substring( indexFirst+1, indexSecond );
                buttonAnimated.setIcon(new ImageIcon("Images/" + newNameIcon + ".png"));
            }
        });

        return buttonAnimated;
    }

    public static JLabel createJLabel(String text, int width, int height, int x, int y, Color textColor) {
        JLabel label = new JLabel(text);
        label.setSize(width, height);
        label.setLocation(x, y);

        label.setFont(Theme.font1);
        label.setForeground(textColor);

        return label;
    }

    public static JTextField createTextField(int width, int height, int x, int y, Color colorText) {
        JTextField textField = new JTextField();
        textField.setSize(width, height);
        textField.setLocation(x, y);

        textField.setOpaque(false);
        textField.setFont(Theme.font2);
        textField.setForeground(colorText);
        textField.setBorder(BorderFactory.createLineBorder(colorText, 2));

        return textField;
    }

    public static JMenuItem createJMenuItem(String text, String name) {
        JMenuItem item = new JMenuItem(text);
        item.setName(name);
        item.setFont(Theme.font3);
        item.setOpaque(false);
        item.setForeground(Theme.color4);
        item.setContentAreaFilled(false);
        return item;
    }

    public static JButton createLimpidJButton(String text, String name, int width, int height, int x, int y) {
        JButton button = new JButton(text);
        button.setSize(width, height);
        button.setLocation(x,y);
        button.setName(name);

        button.setMargin(new Insets(0,0,0,0));
        button.setFont(Theme.font1);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setContentAreaFilled(false);
        button.setBorder(null);

        return button;
    }

    public static JPanel createPanel(int width, int height, int x, int y) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setSize(width, height);
        panel.setLocation(x, y);
        return panel;
    }

    public static void repaint(JPanel set) {
        Main.window.getContentPane().add(set, BorderLayout.CENTER);
        Main.window.revalidate();
        Main.window.repaint();
    }


    public static void repaint(JPanel clear, JPanel set) {
        Main.window.getContentPane().remove(clear);
        Main.window.getContentPane().add(set, BorderLayout.CENTER);
        Main.window.revalidate();
        Main.window.repaint();
    }

}

