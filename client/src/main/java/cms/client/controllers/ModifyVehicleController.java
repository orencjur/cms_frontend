package cms.client.controllers;

import cms.client.view.FxmlView;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.security.Provider;

public class ModifyVehicleController extends AbstractController {
  @FXML
  private JFXTextField vehicle;

  @FXML
  private JFXComboBox driverCombo;

  @FXML
  public void initialize() {
      vehicle.setText(stageManager.getSession().getViewingVehicle().getLicencePlate());
      initCombo(driverCombo,stageManager.getSession().getViewingVehicle().getDriver(),"/regularuser/name/truckless");
  }
  private void setOnSuccess(Service<String> service){
      service.setOnSucceeded(WorkerStateEvent  ->{
          if(service.getValue().trim().equals("true")){
              switchSceneEvent(FxmlView.VEHICLES);
          }else {
              displayError("not modified");
          };
          httpErrorWindow(service);
      });
  }

    public void confirm(ActionEvent event) {
      if(driverCombo.getValue().equals("none")){
          displayError("please select driver");
          return;
      }
      Service<String>  service= getRequest("/vehicle/modify?vehicle="+stageManager.getSession().getViewingVehicle().getLicencePlate()+"&driver="+driverCombo.getValue());
      setOnSuccess(service);
    }
    public void retur(ActionEvent event) {
      switchSceneEvent(FxmlView.VEHICLES);
    }


    public void unassign(ActionEvent event) {
        Service<String>  service= getRequest("/vehicle/unassigne?vehicle="+stageManager.getSession().getViewingVehicle().getLicencePlate());
        setOnSuccess(service);
    }

    public void remove(ActionEvent event) {
        Service<String>  service= getRequest("/vehicle/remove?vehicle="+stageManager.getSession().getViewingVehicle().getLicencePlate());
        setOnSuccess(service);
    }


}
