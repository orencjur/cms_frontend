package cz.cvut.fel.pjv.cms.client.controllers;

import cz.cvut.fel.pjv.cms.client.async.TimeoutServiceSynchronizer;
import cz.cvut.fel.pjv.cms.client.controllers.validators.Validator;
import cz.cvut.fel.pjv.cms.client.fxmlhandler.StageManager;
import cz.cvut.fel.pjv.cms.client.async.Timeout;
import cz.cvut.fel.pjv.cms.client.http.HtttpService;
import cz.cvut.fel.pjv.cms.client.view.FxmlView;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * This controller contains methods that operate components shared by all the other controllers.
 * Controllers then use FXML views located in resources/views
 */
public abstract class AbstractController {

    protected HashSet<TimeoutServiceSynchronizer> initSynchronizers = new HashSet<>();
    boolean init;
    private Service<String> service;
    private TimeoutServiceSynchronizer lastRequest;
    protected final StageManager stageManager = StageManager.getInstance();
    protected final static Logger LOG = Logger.getLogger(AbstractController.class);
    protected Validator validator;

    AbstractController() {
        validator = new Validator(this);
    }

    protected void switchSceneEvent(FxmlView view) {
        if (lastRequest != null) {
            lastRequest.stop();
        }
        if (view.equals(FxmlView.NOCONNECTION)) {
            lastRequest = setTimeout(10, service);
            displayError("no connection");
            return;
        }
        shutdown();
        LOG.info("switching scene to " + view);
        stageManager.switchScene(view);
    }


    protected TimeoutServiceSynchronizer setTimeout(int seconds, Service<String> requestSer) {
        Timeout timeout = new Timeout();
        timeout.timeout(seconds);
        TimeoutServiceSynchronizer synchronizer = new TimeoutServiceSynchronizer(timeout, requestSer);
        lastRequest = synchronizer;
        return synchronizer;
    }

    protected Service<String> getRequest(String url) {
        if (service != null) {
            service.cancel();
        }
        Service<String> ser = new HtttpService(url);
        ser.start();
        service = ser;
        return ser;
    }

    protected Service<String> getInitRequest(String url) {
        Service<String> ser = new HtttpService(url);
        ser.start();
        service = ser;
        return ser;
    }

    protected boolean httpErrorWindow(Service<String> service) {
        if (service.getValue().equals("NOCONNECTION")) {
            displayError("no connection");
            setTimeout(60, service);
            return false;
        }
        LOG.debug(error);
        if (error.getText().equals("no connection")) {
            displayError("");
        }
        return true;
    }

    protected static List<String> parse(String toparse) {
        return Arrays.asList(toparse.split("@", 0));
    }


    public void shutdown() {
        if (service != null) {
            service.cancel();
        }
        for (TimeoutServiceSynchronizer s : initSynchronizers) {
            s.stop();
        }
    }

    protected void restart() {
        for (TimeoutServiceSynchronizer s : initSynchronizers) {
            s.restart();
        }
    }

    protected void stop(List<TimeoutServiceSynchronizer> list) {
        for (TimeoutServiceSynchronizer s : list) {
            s.stop();
        }
    }

    //Back Buttons ------------------------------------------------------------------------------------------------

    @FXML
    protected void home() {
        if (stageManager.getSession().getLoggedRole() == FxmlView.DISPATCH) {
            switchSceneEvent(FxmlView.DISPATCH);
        } else if (stageManager.getSession().getLoggedRole() == FxmlView.DRIVER) {
            switchSceneEvent(FxmlView.DRIVER);
        } else {
            LOG.debug("bad session");
            switchSceneEvent(FxmlView.LOGIN);
        }
    }

    @FXML
    protected void cancel() {
        switchSceneEvent(FxmlView.MSGBOARD);
    }

    @FXML
    protected void logout() {
        stageManager.emptySession();
        switchSceneEvent(FxmlView.LOGIN);
    }


    @FXML
    private Label error;

    public void displayError(String message) {
        error.setText(message);
    }

    //Inits ---------------------------------------------------------------------------------------------
    protected void initCombo(ComboBox vehicle, String url) {
        Service<String> service = getInitRequest(url);
        service.setOnSucceeded((WorkerStateEvent event) -> {
            httpErrorWindow(service);
            vehicle.getItems().clear();
            vehicle.getItems().addAll(parse(service.getValue()));
            initSynchronizers.add(setTimeout(60, service));
        });
    }

    protected void initCombo(ComboBox vehicle, String defaultValue, String url) {
        Service<String> service = getInitRequest(url);
        service.setOnSucceeded((WorkerStateEvent event) -> {
            httpErrorWindow(service);
            vehicle.getItems().clear();
            vehicle.getItems().addAll(parse(service.getValue()));
            //vehicle.getItems().add(null);
            LOG.debug(vehicle + defaultValue);
            vehicle.getItems().add(defaultValue);
            vehicle.setValue(defaultValue);
            LOG.debug(vehicle.getValue());
            initSynchronizers.add(setTimeout(60, service));
        });
    }

    @FXML
    protected void userManaagement(ActionEvent event) {
        switchSceneEvent(FxmlView.USERMANAGEMENT);
    }


}
