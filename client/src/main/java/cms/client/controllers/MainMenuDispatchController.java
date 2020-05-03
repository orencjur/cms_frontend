package cms.client.controllers;

import cms.client.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainMenuDispatchController extends AbstractController {

    @FXML
    public void shipments(ActionEvent event){
        switchSceneEvent(FxmlView.SHIPMENT);
    }
    @FXML
    public void msgBoard(ActionEvent event) {
        switchSceneEvent(FxmlView.MSGBOARD);
    }
}
