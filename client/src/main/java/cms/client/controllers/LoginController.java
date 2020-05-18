package cms.client.controllers;

import cms.client.controllers.entityhelpers.Popup;
import cms.client.view.FxmlView;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;


public class LoginController extends AbstractController {

    @FXML
    private TextField username;

    @FXML
    private TextField password;





    public void login(ActionEvent actionEvent) {
        String user = username.getText().trim(); //textfield add some rubbish
        String pass = password.getText().trim();
        LOG.debug("username= "+user+" password= "+ pass);
        if(user.equals("") || pass.equals("") ){
            displayError("Please fill all textfields");
        }else {
            LOG.debug("login submit");
            String url ="/login?username="+user+"&password="+pass;
            Service<String> service = getRequest(url);
            service.setOnSucceeded((WorkerStateEvent event) -> {
                stageManager.getSession().setLoggedUser(user);
                stageManager.getSession().setLoggedRole(FxmlView.valueOf(service.getValue()));
                if(FxmlView.valueOf(service.getValue()).equals(FxmlView.LOGIN)){
                    displayError("Invalid username or password");
                    return;
                }
                switchSceneEvent(FxmlView.valueOf(service.getValue()));
            });
        }
    }
}
