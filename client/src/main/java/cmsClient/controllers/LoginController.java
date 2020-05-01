package cmsClient.controllers;

import cmsClient.Http.HtttpHandler;
import cmsClient.view.FxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginController extends AbstractController {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    public void login(ActionEvent actionEvent) {
        LOG.debug("username= "+username.getText()+" password= "+ password.getText());
        if(username.getText()==null || password.getText()==null){//bitch dont work
            System.out.println("kokot");
            //vokno please fill username and password
        }else {
            LOG.debug("login submit");
            String desider = getRequest("/login?username="+username.getText()+"&password="+password.getText());
            stageManager.switchScene(FxmlView.valueOf(desider));
        }
    }
}
