package cms.client.controllers.ModifyUserControllers;

import cms.client.controllers.AbstractController;
import cms.client.controllers.ModifyUserController;
import cms.client.controllers.entityhelpers.User;
import cms.client.view.FxmlView;
import javafx.concurrent.Service;

import java.security.Provider;

public class ModifyDispatcherStrategy extends AbstractController implements ModifyUserInterface {
    private ModifyUserController parent;

    public ModifyDispatcherStrategy(ModifyUserController modifyUserController) {
        parent=modifyUserController;
    }



    @Override
    public void save() {

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

    @Override
    public void delete() {
        Service<String> service = getRequest("/manager/delete?username="+parent.getUsername().getText().trim());
        service.setOnSucceeded(workerStateEvent -> {
            if(service.getValue().equals(true)){
                switchSceneEvent(FxmlView.USERMANAGEMENT);
            }else{
                LOG.debug("username not found");
            }
        });
    }


}
