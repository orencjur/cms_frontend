package cms.client.controllers.modifyuser;

import cms.client.controllers.AbstractController;
import cms.client.controllers.ModifyUserController;
import cms.client.controllers.entityhelpers.User;
import cms.client.view.FxmlView;
import javafx.concurrent.Service;

public class ModifyDriverStrategy extends ModifyUserController implements ModifyUserInterface {
    private ModifyUserController parent;
    public ModifyDriverStrategy(ModifyUserController modifyUserController) {
        parent=modifyUserController;
    }


    @Override
    public void save() {
      Service<String> service = getRequest("/regularuser/modify?previoususername="+stageManager.getSession().getViewingUser().getUsername()+"&username="+parent.getUsername().getText().trim()+"&name="+parent.getFname().getText().trim()+":"+parent.getLname().getText().trim()+"&password="+parent.getPass().getText().trim()+"&licence="+parent.getLicense().getText().trim()+"&vehicle="+parent.getVehicle().getValue());
      setOnSucceeded( service);
    }

    @Override
    public void init(User user) {
        parent.getFname().setText(user.getName());
        parent.getLname().setText(user.getSurname());
        parent.getUsername().setText(user.getUsername());
        initCombo(parent.getVehicle(),user.getUserVehicle(),"/vehicles/available");
        parent.getLicense().setText(user.getLicenceNumber());
    }

    @Override
    public void delete() {
        Service<String> service = getRequest("/regularuser/delete?username="+parent.getUsername().getText().trim());
       setOnSucceeded(service);
    }
}
