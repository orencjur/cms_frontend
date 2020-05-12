package cms.client.controllers;

import cms.client.view.FxmlView;
import javafx.event.ActionEvent;

public class MainMenuDriver extends AbstractController{
    public void msgboard(ActionEvent event) {
        switchSceneEvent(FxmlView.MSGBOARD);
    }

    public void shipments(ActionEvent event) {
        switchSceneEvent(FxmlView.DRIVER_SHIPMENT);
    }
}
