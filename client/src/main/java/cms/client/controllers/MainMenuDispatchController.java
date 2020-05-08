package cms.client.controllers;

import cms.client.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainMenuDispatchController extends AbstractController {


    public Label name;

    @FXML
public void initialize() {
    name.setText(stageManager.getSession().getLoggedUser());
}

    @FXML
    public void shipments(ActionEvent event){
        switchSceneEvent(FxmlView.SHIPMENT);
    }
    @FXML
    public void msgBoard(ActionEvent event) {
        switchSceneEvent(FxmlView.MSGBOARD);
    }


}
