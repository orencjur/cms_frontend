package cmsClient;

import cmsClient.FxmlHandler.SpringFXMLLoader;
import cmsClient.FxmlHandler.StageManager;
import cmsClient.Http.HtttpHandler;
import cmsClient.view.FxmlView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

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
            HtttpHandler.getInstance().createFactory("http://localhost:8080/cms");
            displayInitialScene();
            LOG.info("app started");

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


