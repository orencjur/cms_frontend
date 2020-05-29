package cz.cvut.fel.pjv.cms.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ErrorController extends AbstractController {

    @FXML
    private Label lblLogin1;

    @FXML
    public void initialize() {
        lblLogin1.setText(stageManager.getSession().getError());
    }
}
