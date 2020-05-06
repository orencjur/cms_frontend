package cms.client;

import cms.client.fxmlhandler.StageManager;
import cms.client.view.FxmlView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;



public class Client extends Application {

    private final static Logger LOG = Logger.getLogger(Client.class);
    private final StageManager stageManager = StageManager.getInstance();
    private static Scene scene;

        public static void main(String[] args){
            Application.launch(args);
        }

        @Override
        public void start(Stage stage) throws IOException {
            displayInitialScene();
            LOG.info("app started");

        }



        @Override
        public void stop() throws Exception {
            Platform.exit();
            super.stop(); //To change body of generated methods, choose Tools | Templates.
            System.exit(0);
        }



    static void setRoot(String fxml) throws IOException {
            scene.setRoot(loadFXML(fxml));
        }

        private static Parent loadFXML(String fxml) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource(fxml + ".fxml"));
            return fxmlLoader.load();
        }
    protected void displayInitialScene() {
        stageManager.switchScene(FxmlView.LOGIN);

    }



}


