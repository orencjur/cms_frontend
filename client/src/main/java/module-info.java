

module cms.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires com.jfoenix;
    requires log4j;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.commons.codec;

    opens cms.client.controllers to javafx.fxml;
    opens cms.client.controllers.entityhelpers to javafx.base;
    exports cms.client;
}