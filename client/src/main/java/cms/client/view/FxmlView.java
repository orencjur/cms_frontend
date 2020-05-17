package cms.client.view;

import java.util.ResourceBundle;

public enum FxmlView {

    /**
     * directories for Views are stored here
     */
    //TODO loginfailed not have fxml yet
    VEHICLES{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("vehicles.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/VehicleManagement.fxml";
        }
    },
    MODIFYUSER{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("modifyuser.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/ModifyUser.fxml";
        }
    },
    ERROR{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("error.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/Error.fxml";
        }
    },
    NEWUSER{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("newuser.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/NewUser.fxml";
        }
    },
    USERMANAGEMENT{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("userman.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/UserManagement.fxml";
        }
    },
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
    },

    POPUP{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("error.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/Error.fxml";
        }
    },

    DRIVER_SHIPMENT{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("shipment.title");
        }

        @Override
        public String getFxmlFile() {
            return "/views/DriverShipment.fxml";
        }
    };




    public abstract String getTitle();
    public abstract String getFxmlFile();

    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}