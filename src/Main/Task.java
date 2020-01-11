package Main;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Task implements Serializable {
    private GregorianCalendar date;
    private String description;
    private boolean isDone;

    public Task(GregorianCalendar date, String description, boolean isDone) {
        this.date = date;
        this.description = description;
        this.isDone = isDone;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "" + this.date.get(1) + " " + (this.date.get(2)+1) + " "+ this.date.get(5) + " " + this.isDone + " " + this.description;
    }
}