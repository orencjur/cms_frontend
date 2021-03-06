package cz.cvut.fel.pjv.cms.client.controllers.entityhelpers;

import cz.cvut.fel.pjv.cms.client.view.FxmlView;
import javafx.beans.property.SimpleStringProperty;


/**
 * Parsing of the Message entity before displaying it
 */
public class Message {
    private SimpleStringProperty user;
    private SimpleStringProperty date;
    private SimpleStringProperty time;
    private SimpleStringProperty content;
    private FxmlView sender;
    private String id;


    public FxmlView getSender() {
        return sender;
    }


    public String getUser() {
        return user.get();
    }

    public SimpleStringProperty userProperty() {
        return user;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public String getContent() {
        return content.get();
    }

    public SimpleStringProperty contentProperty() {
        return content;
    }

    public String getId() {
        return id;
    }

    public Message(String user, String time, String content, String sender, String id) {
        this.user = new SimpleStringProperty(user);
        this.id = id;
        String[] datetime = time.split(" ");
        this.date = new SimpleStringProperty(datetime[0]);
        this.time = new SimpleStringProperty(datetime[1]);
        this.content = new SimpleStringProperty(content.replaceAll(":", " "));
        this.sender = FxmlView.valueOf(sender);
    }
}
