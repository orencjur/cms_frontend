package cmsClient.controllers;

import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class NewMessageController extends AbstractController {
    @FXML
    ComboBox<String> users;

    @FXML
    TextField content;

    @FXML
    public void initialize() {
        Service<String> service = getRequest("/users");
        service.setOnSucceeded((WorkerStateEvent event) -> {
            users.getItems().clear();
            users.getItems().addAll(parse(service.getValue()));
            setTimeout(60,service);
        });
    }

    public void send(ActionEvent event) {
        if(content.getText().trim().equals("")||users.getSelectionModel().isEmpty()){
            LOG.debug("fill everythig");
        }else {
            String url = "/send?user="+users.getValue()+"&content="+content.getText().trim();
            Service<String> service = getRequest(url);
            service.setOnSucceeded(e -> {
                LOG.debug("message send");
            });
        }
    }
}
