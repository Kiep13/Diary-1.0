package Main;

import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class Information {

    private static UIManager UI = new UIManager();

    public static String CheckInputDate(String date) {

        StringTokenizer strbox;
        String[] number;

        strbox = new StringTokenizer(date, ".");
        if(strbox.countTokens() != 3) {
            return "Некорректная дата";
        }

        number = new String[strbox.countTokens()];
        number[0] = strbox.nextToken();
        number[1] = strbox.nextToken();
        number[2] = strbox.nextToken();

        int day, month, year;

        try {
            year = Integer.parseInt(number[2]);
        } catch (Exception e) {
            return "Ошибки при вводе года";
        }

        try {
            month = Integer.parseInt(number[1]);
        } catch (Exception e) {
            return "Ошибки при вводе месяца";
        }

        try {
            day = Integer.parseInt(number[0]);
        } catch (Exception e) {
            return "Ошибки при вводе дня";
        }

        Calendar calendar = new GregorianCalendar();

        if(year < calendar.get(1)) {
            return "Этот год уже давно закончился";
        }

        if(year >  (calendar.get(1))+10) {
            return "Не надо так сильно заморачиваться";
        }

        if(month < 1 || month > 12) {
            return "Нет такого месяца";
        }

        if(year == calendar.get(1) && month < (calendar.get(2))+1) {
            return "Этот месяц уже прошел";
        }

        if(year == calendar.get(1) && month == (calendar.get(2))+1 && day < calendar.get(5)) {
            return "Этот день уже прошел";
        }

        if((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 ||  month == 12) && (day < 1 || day > 31)) {
            return "Нет такого дня";
        }

        if((month == 4 || month == 6 || month == 9 || month == 11) && (day < 1 || day > 30)) {
            return "Нет такого дня";
        }

        if((year % 4 == 0) && month == 2 && (day < 1 || day > 29)) {
            return "Нет такого дня";
        }

        if((year % 4 != 0) && month == 2 && (day < 1 || day > 28)) {
            return "Нет такого дня";
        }

        return "";
    }

    public static void ShowErrorMessage(String title, String message) {

        UI.put("OptionPane.background", Theme.color5);
        UI.put("Panel.background", Theme.color5);

        JOptionPane.showMessageDialog(Main.window, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void ShowInformationMessage(String title, String message) {

        UI.put("OptionPane.background", Theme.color5);
        UI.put("Panel.background", Theme.color5);

        JOptionPane.showMessageDialog(Main.window, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
