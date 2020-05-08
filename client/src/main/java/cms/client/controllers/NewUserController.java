package cms.client.controllers;

import cms.client.view.FxmlView;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class NewUserController extends AbstractController {

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
            initVehicles(vehicle);
            licenceNumber.setVisible(true);
            vehicle.setVisible(true);
        }else {
            licenceNumber.setVisible(false);
            vehicle.setVisible(false);
        }
    }
    public void send(ActionEvent event) {
        if(username.getText().trim().equals("")||firstName.getText().trim().equals("")||password.getText().trim().equals("")||lastName.getText().trim().equals("")){
            LOG.debug("please fill all");
        }
        if(stageManager.getSession().isCreatingDriver()){
            createDriver();
        }else {
            createDispatcher();
        }
    }

    private Service<String> createDriver() {
        if(vehicle.getSelectionModel().isEmpty()||licenceNumber.getText().trim().equals("")){
            LOG.debug("fill all");
        }
        return null;
    }

    private void createDispatcher() {
    }


    public void userManaagement(ActionEvent event) {
        switchSceneEvent(FxmlView.USERMANAGEMENT);
    }
}
