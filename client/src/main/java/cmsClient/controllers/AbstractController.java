package cmsClient.controllers;

import cmsClient.FxmlHandler.StageManager;
import cmsClient.Http.HtttpHandler;
import cmsClient.view.FxmlView;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public abstract class  AbstractController {


    private Thread thread;
    protected final StageManager stageManager =StageManager.getInstance();
    protected final static Logger LOG = Logger.getLogger(AbstractController.class);


    protected void getRequest(String url)  {
        Service<String> ser = new HtttpHandler(url);
        ser.setOnSucceeded((WorkerStateEvent event) -> {
            LOG.debug(ser.getMessage()+ser.getValue());
            if(ser.getValue().trim()=="noconnection"){
                timeout(10,url);
                return;
            }
            stageManager.switchScene(FxmlView.valueOf(ser.getValue()));
        });

        ser.start();
    }
    private void HttpErrorWindow(String response){

    }
    protected static List<String> parse(String toparse){
        return  Arrays.asList(toparse.split("@",0));
    }
    private void timeout(int seconds,String url)  {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            public void run() {
                getRequest(url);
            }
        };

        timer.schedule(task, seconds * 1000);
    }



}
