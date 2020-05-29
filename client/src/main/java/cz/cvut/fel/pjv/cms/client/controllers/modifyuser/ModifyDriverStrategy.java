package cz.cvut.fel.pjv.cms.client.controllers.modifyuser;

import cz.cvut.fel.pjv.cms.client.controllers.ModifyUserController;
import cz.cvut.fel.pjv.cms.client.controllers.entityhelpers.User;
import javafx.concurrent.Service;

public class ModifyDriverStrategy extends ModifyUserController implements ModifyUserInterface {
    private ModifyUserController parent;

    public ModifyDriverStrategy(ModifyUserController modifyUserController) {
        parent = modifyUserController;
    }


    @Override
    public void save() {
        Service<String> service = getRequest("/regularuser/modify?previoususername=" + stageManager.getSession().getViewingUser().getUsername() + "&username=" + parent.getUsername().getText().trim() + "&name=" + parent.getFname().getText().trim() + ":" + parent.getLname().getText().trim() + "&password=" + parent.getPass().getText().trim() + "&licence=" + parent.getLicense().getText().trim() + "&vehicle=" + parent.getVehicle().getValue());
        parent.setOnSucceeded(service);
    }

    @Override
    public void init(User user) {
        parent.getFname().setText(user.getName());
        parent.getLname().setText(user.getSurname());
        parent.getUsername().setText(user.getUsername());
        parent.initCombo("/vehicles/available");
        parent.getLicense().setText(user.getLicenceNumber());
    }

    @Override
    public void delete() {
        Service<String> service = getRequest("/regularuser/delete?username=" + parent.getUsername().getText().trim());
        parent.setOnSucceeded(service);
    }


}
