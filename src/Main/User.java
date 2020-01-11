package Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class User implements Serializable {

    public ArrayList<Task> taskList;
    public String nameColorTheme;
    public boolean autoClear;

    public User() {

        this.taskList = new ArrayList<Task>();
        this.nameColorTheme = "Стандарт";
        this.autoClear = false;
    }

    public void sortTasks() {
        Collections.sort(this.taskList, new Comparator<Task>() {
            public int compare(Task task1, Task task2) {
                if(task1.getDate().get(1) != task2.getDate().get(1)) {
                    return (task1.getDate().get(1) - task2.getDate().get(1));
                } else if(task1.getDate().get(1) == task2.getDate().get(1) && task1.getDate().get(2) != task2.getDate().get(2)) {
                    return (task1.getDate().get(2) - task2.getDate().get(2));
                } else if(task1.getDate().get(1) == task2.getDate().get(1) && task1.getDate().get(2) == task2.getDate().get(2) && task1.getDate().get(5) != task2.getDate().get(5)) {
                    return (task1.getDate().get(5) - task2.getDate().get(5));
                }
                return 0;
            }
        });
    }

    public void saveUserData() {

        String name = "data.bin";
        User userBox = Main.user;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name))) {
            oos.writeObject(userBox);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void loadUserData() {

        String name =  "data.bin";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name))) {
            User userBox = (User) ois.readObject();
            Main.user = userBox;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
