package cms.client.controllers.entityhelpers;

import javafx.beans.property.SimpleStringProperty;

public class Message {
    public SimpleStringProperty user;
    public SimpleStringProperty date;
    public SimpleStringProperty time;
    public SimpleStringProperty content;

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

    public Message(String user, String time, String content){
        this.user=new SimpleStringProperty(user);
        String []datetime = time.split(" ");
        this.date= new SimpleStringProperty(datetime[0]);
        this.time= new SimpleStringProperty(datetime[1]);
        this.content= new SimpleStringProperty(content.replaceAll(":"," "));
    }
}
