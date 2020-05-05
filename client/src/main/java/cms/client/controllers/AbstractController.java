package cms.client.controllers;

import cms.client.async.TimeoutSericeSynchronizer;
import cms.client.fxmlhandler.StageManager;
import cms.client.async.Timeout;
import cms.client.http.HtttpService;
import cms.client.view.FxmlView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public abstract class  AbstractController {

    protected HashSet<TimeoutSericeSynchronizer> initSynchronizers = new HashSet<>();
    boolean init;
    private Service<String> service;
    protected final StageManager stageManager =StageManager.getInstance();
    protected final static Logger LOG = Logger.getLogger(AbstractController.class);



    protected void switchSceneEvent(FxmlView view){
        shutdown();
        if(view.equals(FxmlView.NOCONNECTION)){
            setTimeout(10,service);
            //timeout(10,url);
            return;
        }
        stageManager.switchScene(view);
    }

    protected TimeoutSericeSynchronizer setTimeout(int seconds,Service<String> requestSer){
        Timeout timeout = new Timeout();
        timeout.timeout(seconds);
        TimeoutSericeSynchronizer synchronizer = new TimeoutSericeSynchronizer(timeout,service);
        return synchronizer;
       /* timeout.intProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(final ObservableValue<? extends Number> observable,
                                final Number oldValue, final Number newValue) {
                    Platform.runLater(() -> {
                       requestSer.restart();
                    });

            }
        });*/
    }

    protected Service<String> getRequest(String url){
        Service<String> ser = new HtttpService(url);
        ser.start();
        service =ser;
        return ser;
    }
    private void HttpErrorWindow(String response){

    }
    protected static List<String> parse(String toparse){
        return  Arrays.asList(toparse.split("@",0));
    }


    public void shutdown(){
        for (TimeoutSericeSynchronizer s:initSynchronizers){
            s.stop();
        }
    }

    protected void restart(){
        for (TimeoutSericeSynchronizer s:initSynchronizers){
            s.restart();
        }
    }


}
