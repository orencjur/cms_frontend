package cz.cvut.fel.pjv.cms.client.controllers;

import cz.cvut.fel.pjv.cms.client.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controlls the driver main menu that give the user access to other functionalities
 */
public class MainMenuDriver extends AbstractController {

    @FXML
    public void msgBoard(ActionEvent event) {
        switchSceneEvent(FxmlView.MSGBOARD);
    }

    @FXML
    public void shipments(ActionEvent event) {
        switchSceneEvent(FxmlView.DRIVER_SHIPMENT);
    }

    @FXML
    public Label name;

    @FXML
    public void initialize() {
        name.setText("Logged as " + stageManager.getSession().getLoggedUser());
    }
}
