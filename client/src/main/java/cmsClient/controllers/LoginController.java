package cmsClient.controllers;

import cmsClient.Http.HtttpHandler;
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
        if(username.getText()=="" || password.getText()==""){
            //vokno please fill username and password
        }else {
            getRequest("/login?username="+username.getText()+"&password="+password.getText());

        }
    }
}
