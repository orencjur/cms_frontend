package cms.client.controllers;

import cms.client.view.FxmlView;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.apache.commons.codec.binary.Hex;

public class NewMessageController extends AbstractController {
    @FXML
    ComboBox<String> users;

    @FXML
    TextField content;

    @FXML
    public void initialize() {
        Service<String> service;
        if(stageManager.getSession().getLoggedRole()==FxmlView.DISPATCH){
            service=getInitRequest("/regularuser");
        }else if (stageManager.getSession().getLoggedRole()==FxmlView.DRIVER){
            service=getInitRequest("/manager");
        }else {
            LOG.debug("smth strange happened logged as:"+stageManager.getSession().getLoggedUser()+"with role"+stageManager.getSession().getLoggedRole() );
            stageManager.switchScene(FxmlView.LOGIN);
            return;
        }
        service.setOnSucceeded((WorkerStateEvent event) -> {
            httpErrorWindow(service.getValue());
            users.getItems().clear();
            users.getItems().addAll(parse(service.getValue()));
            initSynchronizers.add(setTimeout(60,service));
        });
    }

    public void send(ActionEvent event) {
        String cont= content.getText();
        if(cont.trim().equals("")||users.getSelectionModel().isEmpty()){
            LOG.debug("fill everythig");
            displayError("Please fill every field");
        }else {
            cont=cont.replaceAll("\\s",":");
            String receiver = users.getValue();
            String url ="/send?dispatcher=";
            if(stageManager.getSession().getLoggedRole()== FxmlView.DRIVER) {
                 url = url + receiver +"&driver="+stageManager.getSession().getLoggedUser()+"&sender="+stageManager.getSession().getLoggedRole()+"&content=" + cont;
            }else if(stageManager.getSession().getLoggedRole()==FxmlView.DISPATCH){
                url = url + stageManager.getSession().getLoggedUser()+"&driver="+receiver+"&sender="+stageManager.getSession().getLoggedRole()+"&content=" + cont;
            }else {
                stageManager.switchScene(FxmlView.LOGIN);
                LOG.debug("smth strange happened logged as:"+stageManager.getSession().getLoggedUser()+"with role"+stageManager.getSession().getLoggedRole() );
            }
            Service<String> service = getRequest(url);
            service.setOnSucceeded(e -> {
                LOG.debug("message send");
                stageManager.switchScene(FxmlView.MSGBOARD);
            });
        }
    }
}
