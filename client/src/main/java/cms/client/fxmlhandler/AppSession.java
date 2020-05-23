package cms.client.fxmlhandler;

import cms.client.controllers.entityhelpers.Message;
import cms.client.controllers.entityhelpers.User;
import cms.client.view.FxmlView;

public class AppSession {

    private String loggedUser;
    private FxmlView loggedRole;
    private Message viewingMessage;
    private boolean creatingDriver;
    private User viewingUser;
    private String Error;

    public String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    public FxmlView getLoggedRole() {
        return loggedRole;
    }

    public void setLoggedRole(FxmlView loggedRole) {
        this.loggedRole = loggedRole;
    }

    public Message getViewingMessage() {
        return viewingMessage;
    }

    public void setViewingMessage(Message viewingMessage) {
        this.viewingMessage = viewingMessage;
    }

    public boolean isCreatingDriver() {
        return creatingDriver;
    }

    public void setCreatingDriver(boolean creatingDriver) {
        this.creatingDriver = creatingDriver;
    }

    public User getViewingUser() {
        return viewingUser;
    }

    public void setViewingUser(User viewingUser) {
        this.viewingUser = viewingUser;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }
}
