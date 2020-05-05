package cms.client.fxmlhandler;

import cms.client.view.FxmlView;

public class AppSession {

    private String loggedUser;
    private FxmlView loggedRole;

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
