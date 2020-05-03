package cmsClient.controllers;

import cmsClient.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenuDispatchController extends AbstractController {

    @FXML
    public void shipments(ActionEvent event){
        switchSceneEvent("SHIPMENT");
    }
}
