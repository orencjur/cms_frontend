package cms.client.fxmlhandler;

import cms.client.controllers.entityhelpers.Message;
import cms.client.controllers.entityhelpers.User;
import cms.client.view.FxmlView;

public class AppSession {

    private String loggedUser;
    private FxmlView loggedRole;
    private Message ViewingMessage;
    private boolean CreatingDriver;
    private User ViewingUser;
    private String Error;

    public User getViewingUser() {
        return ViewingUser;
    }

    public void setViewingUser(User viewingUser) {
        ViewingUser = viewingUser;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }




    public boolean isCreatingDriver() {
        return CreatingDriver;
    }



    public void setCreatingDriver(boolean creatingDriver) {
        CreatingDriver = creatingDriver;
    }



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
