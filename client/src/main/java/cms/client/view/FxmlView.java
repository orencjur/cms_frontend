package cms.client.view;

import java.util.ResourceBundle;

public enum FxmlView {

    /**
     * directories for Views are stored here
     */
    //TODO loginfailed not have fxml yet
    MSGVIEW{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("msgview.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/MessageView.fxml";
        }
    },
    NEW{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("new.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/NewMessage.fxml";
        }
    },
    MSGBOARD{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("msgboard.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/MsgBoard.fxml";
        }
    },
    NOCONNECTION{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("driver.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/Login.fxml";
        }
    },
    LOGINFAILED {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("driver.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/MainMenuDispatch.fxml";
        }
    },
    SHIPMENT {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("shipment.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/ShipmentManagement.fxml";
        }
    },
    DRIVER {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("driver.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/MainMenuDriver.fxml";
        }
    },
    LOGIN {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/Login.fxml";
        }
    },

    DISPATCH {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("dispatch.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/MainMenuDispatch.fxml";
        }
    };

    public abstract String getTitle();
    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}