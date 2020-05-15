package cms.client.controllers.modifyuser;

import cms.client.controllers.AbstractController;
import cms.client.controllers.entityhelpers.User;
import cms.client.view.FxmlView;
import javafx.concurrent.Service;

public interface ModifyUserInterface  {
    public void save();
    public void init(User user);
    public void delete();



}
