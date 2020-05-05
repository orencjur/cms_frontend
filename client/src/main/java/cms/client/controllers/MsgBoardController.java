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
        if(init==true){
            restart();
            return;
        }
        Service<String> service = getRequest("/messages?user="+stageManager.getSession().getLoggedUser()+"&role="+stageManager.getSession().getLoggedRole());
        service.setOnSucceeded((WorkerStateEvent event) -> {
            tableView.getItems().clear();
            tableView.getItems().addAll(parse(service.getValue()));
            initSynchronizers.add(setTimeout(2,service));
        });
        init=true;
    }



    public void newMessage(ActionEvent event) {
        shutdown();
        switchSceneEvent(FxmlView.NEW);
    }
}
