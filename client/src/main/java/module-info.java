

module cms.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires log4j;
    requires com.jfoenix;
    requires org.apache.commons.codec;

    opens cms.client.controllers to javafx.fxml;
    opens cms.client.controllers.entityhelpers to javafx.base;
    exports cms.client;
}