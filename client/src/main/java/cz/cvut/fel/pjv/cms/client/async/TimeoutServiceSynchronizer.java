package cz.cvut.fel.pjv.cms.client.async;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;

public class TimeoutServiceSynchronizer {
    private Timeout timeout;
    private Service service;


    public TimeoutServiceSynchronizer(Timeout timeout, Service service) {
        this.timeout = timeout;
        this.service = service;
        timeout.intProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(final ObservableValue<? extends Number> observable,
                                final Number oldValue, final Number newValue) {
                Platform.runLater(() -> {
                    service.restart();
                });

            }
        });
    }

    public void stop(){
        service.cancel();
        timeout.cancel();
    }
    public void restart(){
        service.restart();
    }

    Service getService() {
        return service;
    }
}
