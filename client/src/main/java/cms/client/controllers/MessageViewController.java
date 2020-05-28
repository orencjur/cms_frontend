package cms.client.controllers;

import cms.client.controllers.entityhelpers.Message;
import cms.client.view.FxmlView;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Displays the message
 */
public class MessageViewController extends AbstractController {

    @FXML private Label from;
    @FXML private Label date;
    @FXML private TextField content;


    @FXML
    public void initialize() {
        Message message = stageManager.getSession().getViewingMessage();
        if(message.getSender().equals(stageManager.getSession().getLoggedRole())){
            from.setText(from.getText()+": You to: "+message.getUser());
        }else {
            from.setText(from.getText() + ": " + message.getUser());
        }
       date.setText(date.getText()+": "+message.getDate()+" "+message.getTime());
       content.setText(message.getContent());
    }

    public void delete(ActionEvent actionEvent){
        Service<String> service = getRequest("/message/delete?id="+stageManager.getSession().getViewingMessage().getId());
        service.setOnSucceeded((WorkerStateEvent event) -> {
            if(!httpErrorWindow(service)){
               return;
            }
            switchSceneEvent(FxmlView.MSGBOARD);
        });
    }
}
