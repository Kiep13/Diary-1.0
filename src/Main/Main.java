package Main;

import Panels.PanelAllTask;
import Сomponents.Builder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class Main {

    public static JDialog window;
    private boolean isFixed;
    public static JPanel panel;

    public static User user;

    public Main() {

        user = new User();
        user.loadUserData();
        Theme.setTheme(user.nameColorTheme);

        window = new JDialog();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2 - 175, dim.height/2 - 250);
        window.setSize(350, 500);

        window.setUndecorated(true);
        Shape shape = new RoundRectangle2D.Float(0, 0, window.getWidth(), window.getHeight(),50,50);
        window.setShape(shape);

        Point point = new Point();
        window.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                point.x = me.getX();
                point.y = me.getY();
            }
        });

        window.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent de) {
                if(!isFixed) {
                    Point p = window.getLocation();
                    window.setLocation(p.x + de.getX() - point.x, p.y + de.getY() - point.y);
                }
            }
        });

        JButton buttonExit = Builder.createAnimatedButton(40, 40, 300, 10, "exit");

        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeProgram();
            }
        });

        ImageIcon pushpinOn = new ImageIcon("Images/on.png");
        ImageIcon pushpinOff = new ImageIcon("Images/off.png");

        JToggleButton buttonFixed = Builder.creteAnimatedButton(40,40, 250, 10, "on");

        isFixed = true;

        buttonFixed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(isFixed) {
                    buttonFixed.setIcon(pushpinOff);
                    isFixed = false;
                } else {
                    buttonFixed.setIcon(pushpinOn);
                    isFixed = true;
                }
            }
        });

        window.getContentPane().add(buttonFixed);
        window.getContentPane().add(buttonExit);

        panel = PanelAllTask.getPanel();
        window.getContentPane().add(panel);

        window.setVisible(true);

        class WindowListener extends WindowAdapter {
            public void windowClosing(WindowEvent e){
                    closeProgram();
            }
        }

        window.addWindowListener(new WindowListener());

    }

    public static void closeProgram() {
        user.saveUserData();
        window.dispose();
    }

    public static void main(String [] args) {
        Theme.Standard();
        new Main();
    }
}
