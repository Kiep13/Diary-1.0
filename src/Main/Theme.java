package Main;

import javax.swing.*;
import java.awt.*;

public class Theme {
    public static Color color1 = new Color(191,188,180);
    public static Color color2 = new Color(255,251,240);
    public static Color color3 = new Color(71, 68, 63);
    public static Color color4 = new Color(63,64,60);
    public static Color color5 = new Color(238, 234, 224);
    public static Color color6 = Color.BLACK;

    public static Font font1 = new Font("TimesRoman", Font.PLAIN, 22);
    public static Font font2 = new Font("TimesRoman", Font.ITALIC, 18);
    public static Font font3 = new Font("TimesRoman", Font.PLAIN, 18);

    public static ImageIcon image1 = new ImageIcon("Images/white.jpg");
    public static ImageIcon image2 = new ImageIcon("Images/geometry.jpg");
    public static ImageIcon image3 = new ImageIcon("Images/triangels.jpg");
    public static ImageIcon image4 = new ImageIcon("Images/brushes.jpg");

    public static void Standard() {//Стандарт
        color1 = new Color(191,188,180);//#BFBCB4
        color2 = new Color(255,251,240);//#FFFBF0
        color3 = new Color(128, 125,120);//#807D78
        color4 = new Color(63,64,60);//#403F3C
        color5 = new Color(238, 234, 224);//#E8E4DA
        color6 = Color.BLACK;

        image1 = new ImageIcon("Images/white.jpg");
        image2 = new ImageIcon("Images/geometry.jpg");
        image3 = new ImageIcon("Images/triangels.jpg");
        image4 = new ImageIcon("Images/brushes.jpg");
    }

    public static void Powder() {//Палетка (МБ потом пудра)
        color1 = new Color(128, 65, 54);//#804136
        color2 = new Color(204, 103, 86);//#CC6756
        color3 = new Color(255, 128, 105);//#FF806A
        color4 = new Color(128, 97, 92);//#80615C
        color5 = new Color(255, 194, 184);//#FFC2B8
        color6 = color4;

        image1 = new ImageIcon("Images/roses.jpg");
        image2 = new ImageIcon("Images/powderSea.jpg");
        image3 = new ImageIcon("Images/powderPetals.jpg");
        image4 = new ImageIcon("Images/powderGrass.jpg");
    }

    public static void SeaBreeze() {//Морской бриз
        color1 = new Color(78, 108,128);//#4E6C80
        color2 = new Color(79, 188, 255);//#4FBCFF
        color3 = new Color(156,217,255);//#9CD9FF
        color4 = new Color(52,102,133);//#346685
        color5 = new Color(124,174, 204);//#7CAECC
        color6 = Color.BLACK;

        image1 = new ImageIcon("Images/seaWaves.jpg");
        image2 = new ImageIcon("Images/underSea.jpg");
        image3 = new ImageIcon("Images/sea.jpg");
        image4 = new ImageIcon("Images/seaBreeze.jpg");
    }

    public static void JessicasNightmare() {

        color1 = new Color(119, 86, 125);
        color2 = new Color(232, 99, 225);
        color5 = new Color(241, 174, 252);
        color4 = new Color(111, 15,128);
        color3 = new Color(190, 137, 199);
        color6 = Color.BLACK;

        image1 = new ImageIcon("Images/tressFog.jpg");
        image2 = new ImageIcon("Images/purpleSky.jpg");
        image3 = new ImageIcon("Images/purpleSunset.jpg");
        image4 = new ImageIcon("Images/purpleTown.jpg");

    }

    public static void setTheme(String nameTheme) {
        switch (nameTheme) {
            case "Стандарт": Theme.Standard();break;
            case "Пудра": Theme.Powder();break;
            case "Морской бриз": Theme.SeaBreeze();break;
            case "Кошмар Джессики": Theme.JessicasNightmare();break;
        }
    }

}
