

module cz.cvut.fel.pjv.cms.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires log4j;
    requires com.jfoenix;
    requires org.apache.commons.codec;
    requires org.apache.httpcomponents.httpcore;

    opens cz.cvut.fel.pjv.cms.client.controllers to javafx.fxml;
    opens cz.cvut.fel.pjv.cms.client.controllers.entityhelpers to javafx.base;
    exports cz.cvut.fel.pjv.cms.client;
}