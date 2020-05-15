package cms.client.controllers.ModifyUserControllers;

import cms.client.controllers.AbstractController;
import cms.client.controllers.ModifyUserController;
import cms.client.controllers.entityhelpers.User;

public class ModifyDriverStrategy extends AbstractController implements ModifyUserInterface {
    private ModifyUserController parent;
    public ModifyDriverStrategy(ModifyUserController modifyUserController) {
        parent=modifyUserController;
    }
    @Override
    public void send() {

    }

    @Override
    public void init(User user) {
        parent.getFname().setText(user.getName());
        parent.getLname().setText(user.getSurname());
        parent.getUsername().setText(user.getUsername());
        initVehicleCombo(parent.getVehicle(),user.getUserVehicle());
        parent.getLicense().setText(user.getLicenceNumber());
    }
}
