package cz.cvut.fel.pjv.cms.client.controllers;

import cz.cvut.fel.pjv.cms.client.controllers.modifyuser.ModifyDispatcherStrategy;
import cz.cvut.fel.pjv.cms.client.controllers.modifyuser.ModifyDriverStrategy;
import cz.cvut.fel.pjv.cms.client.controllers.modifyuser.ModifyUserInterface;
import cz.cvut.fel.pjv.cms.client.controllers.entityhelpers.User;
import cz.cvut.fel.pjv.cms.client.view.FxmlView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class ModifyUserController extends AbstractController {


    @FXML
    private JFXButton save;
    @FXML
    private JFXButton delete;
    @FXML
    private Label vehicleLabel;
    @FXML
    private Label licenceLabel;
    @FXML
    private JFXTextField license;
    @FXML
    private JFXTextField fname;
    @FXML
    private JFXTextField lname;
    @FXML
    private JFXTextField pass;
    @FXML
    private JFXComboBox vehicle;
    @FXML
    private JFXTextField username;

    private ModifyUserInterface userModifier;


    private List<TextField> textFields;


    @FXML
    public void initialize() {
        User showingUser = stageManager.getSession().getViewingUser();
        initTextfields();
        if (showingUser.getUserVehicle().equals("Dispatcher")) {
            userModifier = new ModifyDispatcherStrategy(this);
        } else {
            userModifier = new ModifyDriverStrategy(this);
            textFields.add(license);
        }
        LOG.debug(showingUser.getUserVehicle());
        userModifier.init(showingUser);

    }

    public void initCombo(String url) {
        initCombo(vehicle, stageManager.getSession().getViewingUser().getUserVehicle(), url);
    }


    private void initTextfields() {
        textFields = new ArrayList<>();
        textFields.add(lname);
        textFields.add(fname);
        textFields.add(username);
    }

    public void modify(ActionEvent event) {
        save.setDisable(false);
        save.setVisible(true);
        delete.setVisible(true);
        delete.setDisable(false);
        license.setDisable(false);
        fname.setDisable(false);
        lname.setDisable(false);
        pass.setDisable(false);
        vehicle.setDisable(false);
        //username.setDisable(false);
    }


    public void delete(ActionEvent event) {
        userModifier.delete();
    }

    public void save(ActionEvent event) {
        if (!validator.validateTextFields(textFields)) {
            return;
        }
        userModifier.save();
    }

    public void setOnSucceeded(Service<String> service) {
        service.setOnSucceeded(workerStateEvent -> {
            httpErrorWindow(service);

            if (service.getValue().equals("true")) {
                switchSceneEvent(FxmlView.USERMANAGEMENT);
            } else {
                LOG.debug("username not found");
                displayError("vehicle not found");
            }
        });
    }

    //-------------GETTERS-----------------------

    public List<TextField> getTextFields() {
        return textFields;
    }

    public JFXTextField getLicense() {
        return license;
    }

    public JFXTextField getFname() {
        return fname;
    }

    public JFXTextField getLname() {
        return lname;
    }

    public JFXTextField getPass() {
        return pass;
    }

    public JFXComboBox getVehicle() {
        return vehicle;
    }

    public JFXTextField getUsername() {
        return username;
    }

    public Label getVehicleLabel() {
        return vehicleLabel;
    }

    public Label getLicenceLabel() {
        return licenceLabel;
    }


}
