package cms.client.controllers;

import cms.client.controllers.entityhelpers.Message;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MessageViewController extends AbstractController {

    @FXML private Label from;
    @FXML private Label date;
    @FXML private TextField content;


    @FXML
    public void initialize() {
        Message message = stageManager.getSession().getViewingMessage();
        from.setText(from.getText()+": "+message.getUser());
       date.setText(date.getText()+": "+message.getDate()+" "+message.getTime());
       content.setText(message.getContent());
    }
}
