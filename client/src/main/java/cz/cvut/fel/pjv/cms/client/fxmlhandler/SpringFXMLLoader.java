package cz.cvut.fel.pjv.cms.client.fxmlhandler;

import cz.cvut.fel.pjv.cms.client.controllers.AbstractController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


import java.io.IOException;


public class SpringFXMLLoader {

    private FXMLLoader loader;

    public SpringFXMLLoader( ){
    }

    public Parent load(String fxmlPath) throws IOException {
         loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlPath));
        return loader.load();
    }

    public  AbstractController getController() {
        return loader.getController();
    }
}
