package cms.client.fxmlhandler;

import cms.client.view.FxmlView;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.stage.Stage;
import java.util.Objects;
import org.apache.log4j.Logger;

/**
 * Manages switching Scenes on the Primary Stage
 */
public class StageManager {

    private final static Logger LOG = Logger.getLogger(StageManager.class);
    private final Stage primaryStage;
    private final SpringFXMLLoader springFXMLLoader;
    private static  StageManager instance;
    private AppSession session;

    private StageManager(SpringFXMLLoader fxmlLoader,Stage stage){
        this.springFXMLLoader = fxmlLoader;
        this.primaryStage = new Stage();
        session = new AppSession();
    }
    public static synchronized StageManager getInstance(){
        if(instance == null){
            instance = new StageManager(new SpringFXMLLoader(),new Stage());
        }return instance;
    }


    public AppSession getSession() {
        return session;
    }

    public void switchScene(final FxmlView view) {
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
        show(primaryStage,viewRootNodeHierarchy, view.getTitle());
    }

    public void errorPopup(String error) {
        session.setError(error);
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(FxmlView.ERROR.getFxmlFile());
        show(new Stage(),viewRootNodeHierarchy, FxmlView.ERROR.getTitle());
    }


    private void show(final Stage stage,final Parent rootnode, String title) {
        Scene scene = prepareScene(rootnode);
        //scene.getStylesheets().add("/styles/Styles.css");
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.centerOnScreen();

        try {
            stage.show();
        } catch (Exception exception) {
            logAndExit ("Unable to show scene for title" + title,  exception);
        }
    }

    private Scene prepareScene(Parent rootnode){
        if(rootnode ==null){
        }
        Scene scene = primaryStage.getScene();

        if (scene == null) {
            scene = new Scene(rootnode);
        }
        scene.setRoot(rootnode);
        return scene;
    }

    /**
     * Loads the object hierarchy from a FXML document and returns to root node
     * of that hierarchy.
     *
     * @return Parent root node of the FXML document hierarchy
     */
    private Parent loadViewNodeHierarchy(String fxmlFilePath) {
        Parent rootNode = null;
        try {
            rootNode = springFXMLLoader.load(fxmlFilePath);
            Objects.requireNonNull(rootNode, "A Root FXML node must not be null");
        } catch (Exception exception) {
            logAndExit("Unable to load FXML view" + fxmlFilePath, exception);
        }
        return rootNode;
    }

    public void emptySession(){
        this.session=new AppSession();
    }


    private void logAndExit(String errorMsg, Exception exception) {
        LOG.error(errorMsg, exception);
        Platform.exit();
    }

}
