package cmsClient.FxmlHandler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


import java.io.IOException;
import java.util.ResourceBundle;


public class SpringFXMLLoader {




    public SpringFXMLLoader( ){
    }

    public Parent load(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxmlPath));
        return loader.load();
    }

}
