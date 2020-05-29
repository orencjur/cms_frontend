package cz.cvut.fel.pjv.cms.client.controllers.entityhelpers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Popup {


            public void handle() {
                final Stage dialog = new Stage();
                VBox layout = new VBox(2);
                layout.setAlignment(Pos.CENTER);
                Scene scene = new Scene(layout, 300,100);
                dialog.setScene(scene);
                //scene.(FxmlView.POPUP);
                dialog.alwaysOnTopProperty();
                dialog.showAndWait();
            }
        }


