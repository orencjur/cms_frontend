package cmsClient.controllers;

import cmsClient.FxmlHandler.StageManager;
import cmsClient.Http.HtttpHandler;
import cmsClient.async.Timeout;
import cmsClient.view.FxmlView;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class  AbstractController {

    final AtomicInteger count = new AtomicInteger(-1);
    final Timeout timeout = new Timeout();
    protected Service<String> service;
    private Thread thread;
    protected final StageManager stageManager =StageManager.getInstance();
    protected final static Logger LOG = Logger.getLogger(AbstractController.class);



    protected void switchSceneEvent(String response){
        if(response.trim()=="noconnection"){
            setTimeout(10,service);
            //timeout(10,url);
            return;
        }
        stageManager.switchScene(FxmlView.valueOf(response));
    }

    protected void setTimeout(int seconds,Service<String> requestSer){
        timeout.timeout(seconds);
        timeout.intProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(final ObservableValue<? extends Number> observable,
                                final Number oldValue, final Number newValue) {
                if (count.getAndSet(newValue.intValue()) == -1) {
                    Platform.runLater(() -> {
                        requestSer.restart();
                    });
                }

            }
        });
    }

    protected Service<String> getRequest(String url){
        Service<String> ser = new HtttpHandler(url);
        ser.start();
        return ser;
    }
    private void HttpErrorWindow(String response){

    }
    protected static List<String> parse(String toparse){
        return  Arrays.asList(toparse.split("@",0));
    }
    protected void timeout(int seconds, String url)  {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            public void run() {
                getRequest(url);
            }
        };

        timer.schedule(task, seconds * 1000);
    }





}
