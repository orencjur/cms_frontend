

module cmsClient {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires com.jfoenix;
    requires org.slf4j;

    opens cmsClient.controllers to javafx.fxml;
    exports cmsClient;
}