package cms.client.controllers;

import cms.client.view.FxmlView;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MsgBoardController extends AbstractController {

    @FXML private TableView<String> tableView;

    @FXML
    private TextField content;
    @FXML
    public void initialize() {
        Service<String> service = getRequest("/messages");
        service.setOnSucceeded((WorkerStateEvent event) -> {
            tableView.getItems().clear();
            tableView.getItems().addAll(parse(service.getValue()));
            setTimeout(60,service);
        });
    }


    public void newMessage(ActionEvent event) {
        switchSceneEvent(FxmlView.NEW);
    }
}
