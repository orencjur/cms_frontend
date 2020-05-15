package cms.client.controllers.ModifyUserControllers;

import cms.client.controllers.AbstractController;
import cms.client.controllers.ModifyUserController;
import cms.client.controllers.entityhelpers.User;
import cms.client.view.FxmlView;
import javafx.concurrent.Service;

public class ModifyDriverStrategy extends AbstractController implements ModifyUserInterface {
    private ModifyUserController parent;
    public ModifyDriverStrategy(ModifyUserController modifyUserController) {
        parent=modifyUserController;
    }


    @Override
    public void save() {

    }

    @Override
    public void init(User user) {
        parent.getFname().setText(user.getName());
        parent.getLname().setText(user.getSurname());
        parent.getUsername().setText(user.getUsername());
        initVehicleCombo(parent.getVehicle(),user.getUserVehicle());
        parent.getLicense().setText(user.getLicenceNumber());
    }

    @Override
    public void delete() {
        Service<String> service = getRequest("/regularuser/delete?username="+parent.getUsername().getText().trim());
        service.setOnSucceeded(workerStateEvent -> {
            if(service.getValue().equals(true)){
                switchSceneEvent(FxmlView.USERMANAGEMENT);
            }else{
                LOG.debug("username not found");
            }
        });
    }
}
