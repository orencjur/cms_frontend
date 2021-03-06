package cz.cvut.fel.pjv.cms.client.async;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class Timeout {
    private IntegerProperty intProperty;
    private Timer timer;

    public Timeout() {
        intProperty = new SimpleIntegerProperty(this, "int", 0);
    }

    public void timeout(int seconds) {
        timer = new Timer();

        TimerTask task = new TimerTask() {
            public void run() {
                intProperty.setValue(intProperty.getValue() + 1);
            }
        };
        timer.schedule(task, seconds * 1000);
    }

    public IntegerProperty intProperty() {
        return intProperty;
    }

    public void cancel() {
        if (timer != null) {
            timer.cancel();
        }
    }

    public void schedule() {

    }

}
