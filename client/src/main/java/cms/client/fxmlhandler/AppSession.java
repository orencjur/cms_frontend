package cms.client.fxmlhandler;

public class AppSession {

    private String loggedUser;
    private String lasturl;

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getLoggedUser() {
        return loggedUser;
    }
}
