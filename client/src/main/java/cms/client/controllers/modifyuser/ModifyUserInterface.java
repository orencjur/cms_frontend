package cms.client.controllers.modifyuser;

import cms.client.controllers.AbstractController;
import cms.client.controllers.entityhelpers.User;
import cms.client.view.FxmlView;
import javafx.concurrent.Service;

/**
 * This interface is used as a baseline for the CRUD operations with both types of users
 */
public interface ModifyUserInterface  {

    /**
     * creates/saves changes in a user
     */
    public void save();

    /**
     * @param user Gets the user
     */
    public void init(User user);

    /**
     * Delets the user
     */
    public void delete();



}
