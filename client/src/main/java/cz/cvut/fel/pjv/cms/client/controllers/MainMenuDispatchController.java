package cz.cvut.fel.pjv.cms.client.controllers;

import cz.cvut.fel.pjv.cms.client.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controlls the dispatcher main menu that give the user access to other functionalities
 */
public class MainMenuDispatchController extends AbstractController {

    @FXML
    public Label name;

    @FXML
    public void initialize() {
        name.setText("Logged as " + stageManager.getSession().getLoggedUser());
    }

    @FXML
    public void shipments(ActionEvent event) {
        switchSceneEvent(FxmlView.SHIPMENT);
    }

    @FXML
    public void msgBoard(ActionEvent event) {
        switchSceneEvent(FxmlView.MSGBOARD);
    }

    @FXML
    public void users(ActionEvent event) {
        switchSceneEvent(FxmlView.USERMANAGEMENT);
    }

    @FXML
    public void vehicleManagement(ActionEvent event) {
        switchSceneEvent(FxmlView.VEHICLES);
    }
}
