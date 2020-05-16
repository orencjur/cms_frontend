package cms.client.controllers;

import cms.client.view.FxmlView;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NewUserController extends AbstractController {

    @FXML
    private Label licenceLabel;
    @FXML
    private Label vehicleLabel;
    @FXML
    private JFXTextField firstName;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXComboBox vehicle;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField licenceNumber;


    @FXML
    public void initialize() {
        if(stageManager.getSession().isCreatingDriver()){
            initVehicleCombo(vehicle);
            licenceNumber.setVisible(true);
            vehicle.setVisible(true);
            vehicleLabel.setVisible(true);
            licenceLabel.setVisible(true);
        }else {
            licenceNumber.setVisible(false);
            vehicle.setVisible(false);
            vehicleLabel.setVisible(false);
            licenceLabel.setVisible(false);
        }
    }
    public void send(ActionEvent event) {
        if(username.getText().trim().equals("")||firstName.getText().trim().equals("")||password.getText().trim().equals("")||lastName.getText().trim().equals("")){
            LOG.debug("please fill all");
            displayError("Please fill every field");
            return;
        }
        Service<String> service;
        if(stageManager.getSession().isCreatingDriver()){
            service = createDriver();
        }else {
            service = createDispatcher();
        }
        if(service ==null){
            return;
        }
        service.setOnSucceeded((WorkerStateEvent e) -> {
            if(service.getValue().trim().equals("true")){
                switchSceneEvent(FxmlView.USERMANAGEMENT);
            }else {
                LOG.debug("username exists");
                displayError("This username is already taken");
            }
        });
    }

    private Service<String> createDriver() {
        if(licenceNumber.getText().trim().equals("")){
            LOG.debug("fill all");
            displayError("Please fill every field");
            return null;
        }
        return getRequest("/regularuser/create?username="+username.getText().trim()+"&name="+firstName.getText().trim()+":"+lastName.getText().trim()+"&password="+password.getText().trim()+"&licence="+licenceNumber.getText().trim()+"&vehicle="+vehicle.getValue());
    }

    private Service<String> createDispatcher() {
        return getRequest("/manager/create?username="+username.getText().trim()+"&name="+firstName.getText().trim()+":"+lastName.getText().trim()+"&password="+password.getText().trim());
    }



}
