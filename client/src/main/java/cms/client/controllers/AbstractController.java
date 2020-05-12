package cms.client.controllers;

import cms.client.async.TimeoutSericeSynchronizer;
import cms.client.fxmlhandler.AppSession;
import cms.client.fxmlhandler.StageManager;
import cms.client.async.Timeout;
import cms.client.http.HtttpService;
import cms.client.view.FxmlView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public abstract class  AbstractController {

    protected HashSet<TimeoutSericeSynchronizer> initSynchronizers = new HashSet<>();
    boolean init;
    private Service<String> service;
    private TimeoutSericeSynchronizer lastRequest;
    protected final StageManager stageManager =StageManager.getInstance();
    protected final static Logger LOG = Logger.getLogger(AbstractController.class);



    protected void switchSceneEvent(FxmlView view){
        shutdown();
        if(lastRequest!=null) {
            lastRequest.stop();
        }
        if(view.equals(FxmlView.NOCONNECTION)){
            lastRequest=setTimeout(10,service);
            //timeout(10,url);
            return;
        }
        stageManager.switchScene(view);
    }


    public void popup(final Stage primaryStage) {
        new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Stage dialog = new Stage();
                switchSceneEvent(FxmlView.POPUP);
                /*
                dialog.setScene(scene);
                dialog.alwaysOnTopProperty();
                dialog.show();

                 */
            }
        };
    }

    protected TimeoutSericeSynchronizer setTimeout(int seconds,Service<String> requestSer){
        Timeout timeout = new Timeout();
        timeout.timeout(seconds);
        TimeoutSericeSynchronizer synchronizer = new TimeoutSericeSynchronizer(timeout,requestSer);
        return synchronizer;
    }

    protected Service<String> getRequest(String url){
        if(service!=null){
            service.cancel();
        }
        Service<String> ser = new HtttpService(url);
        ser.start();
        service =ser;
        return ser;
    }

    protected Service<String> getInitRequest(String url){
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

    //Back Buttons ------------------------------------------------------------------------------------------------

    @FXML
    protected void home(){
        if(stageManager.getSession().getLoggedRole()==FxmlView.DISPATCH){
            switchSceneEvent(FxmlView.DISPATCH);
        }else if(stageManager.getSession().getLoggedRole()==FxmlView.DRIVER){
            switchSceneEvent(FxmlView.DRIVER);
        }
        else {
            LOG.debug("bad session");
            switchSceneEvent(FxmlView.LOGIN);
        }
    }
    @FXML
    protected void cancel(){
        switchSceneEvent(FxmlView.MSGBOARD);
    }

    @FXML
    protected void logout(){
        stageManager.emptySession();
        switchSceneEvent(FxmlView.LOGIN);
    }

    //Inits ---------------------------------------------------------------------------------------------
    protected void initVehicles(ComboBox<String> vehicle){
        Service<String> service = getInitRequest("/vehicles");
        service.setOnSucceeded((WorkerStateEvent event) -> {
            vehicle.getItems().clear();
            vehicle.getItems().addAll(parse(service.getValue()));
            initSynchronizers.add(setTimeout(60,service));
        });
    }



}
