package cms.client.controllers.ModifyUserControllers;

import cms.client.controllers.ModifyUserController;
import cms.client.controllers.entityhelpers.User;

public class ModifyDispatcherStrategy  implements ModifyUserInterface {
    private ModifyUserController parent;

    public ModifyDispatcherStrategy(ModifyUserController modifyUserController) {
        parent=modifyUserController;
    }

    @Override
    public void send() {

    }

    @Override
    public void init(User user) {
        parent.getLicenceLabel().setVisible(false);
        parent.getVehicleLabel().setVisible(false);
        parent.getVehicle().setVisible(false);
        parent.getLicense().setVisible(false);
        parent.getUsername().setText(user.getUsername());
        parent.getFname().setText(user.getName());
        parent.getLname().setText(user.getSurname());

    }


}
