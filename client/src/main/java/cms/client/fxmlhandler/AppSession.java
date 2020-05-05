package cms.client.fxmlhandler;

import cms.client.controllers.entityhelpers.Message;
import cms.client.view.FxmlView;

public class AppSession {

    private String loggedUser;
    private FxmlView loggedRole;
    private Message ViewingMessage;

    public Message getViewingMessage() {
        return ViewingMessage;
    }

    public void setViewingMessage(Message viewingMessage) {
        ViewingMessage = viewingMessage;
    }


    public FxmlView getLoggedRole() {
        return loggedRole;
    }

    public void setLoggedRole(FxmlView loggedRole) {
        this.loggedRole = loggedRole;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getLoggedUser() {
        return loggedUser;
    }
}
