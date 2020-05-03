package cmsClient.async;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class Timeout {
    private IntegerProperty intProperty;

    public Timeout() {
        intProperty = new SimpleIntegerProperty(this, "int", 0);
    }

    public void timeout(int seconds)  {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            public void run() {
                intProperty.setValue(intProperty.getValue()+1);
            }
        };

        timer.schedule(task, seconds * 1000);
    }
    public IntegerProperty intProperty() {
        return intProperty;
    }

}
