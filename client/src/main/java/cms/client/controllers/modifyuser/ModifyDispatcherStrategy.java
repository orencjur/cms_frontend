package cms.client.controllers.modifyuser;

import cms.client.controllers.AbstractController;
import cms.client.controllers.ModifyUserController;
import cms.client.controllers.entityhelpers.User;
import cms.client.view.FxmlView;
import javafx.concurrent.Service;

public class ModifyDispatcherStrategy extends ModifyUserController implements ModifyUserInterface {
    private ModifyUserController parent;

    public ModifyDispatcherStrategy(ModifyUserController modifyUserController) {
        parent=modifyUserController;
    }


    @Override
    public void save() {

        Service<String> service=getRequest("/manager/modify?previoususername="+stageManager.getSession().getViewingUser().getUsername()+"&username="+parent.getUsername().getText().trim()+"&name="+parent.getFname().getText().trim()+":"+parent.getLname().getText().trim()+"&password="+parent.getPass().getText().trim()+"&vehicle="+parent.getVehicle().getValue());
        setOnSucceeded(service);
    }
        

    @Override
    public void init(User user) {
        parent.getLicenceLabel().setVisible(false);
        parent.getVehicleLabel().setVisible(false);
        parent.getVehicle().setVisible(false);
        getLicense().setVisible(false);
        parent.getUsername().setText(user.getUsername());
        parent.getFname().setText(user.getName());
        parent.getLname().setText(user.getSurname());
    }

    @Override
    public void delete() {
        Service<String> service = getRequest("/manager/delete?username="+parent.getUsername().getText().trim());
        setOnSucceeded(service);
    }


}
