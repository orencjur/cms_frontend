

module cmsClient {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires com.jfoenix;
    requires log4j;
    requires org.apache.httpcomponents.httpcore;

    opens cmsClient.controllers to javafx.fxml;
    exports cmsClient;
}