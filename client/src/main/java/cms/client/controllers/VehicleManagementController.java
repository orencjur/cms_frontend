package cms.client.controllers;

import cms.client.controllers.entityhelpers.EntityFactory;
import cms.client.controllers.entityhelpers.Message;
import cms.client.controllers.entityhelpers.Vehicle;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Arrays;

public class VehicleManagementController extends AbstractController {
    @FXML
    private JFXTextField licencefill;
    @FXML
    private JFXComboBox driverCombo;
    @FXML
    private TableView tableView;

    @FXML
    private TableColumn licencePlate;
    @FXML
    private TableColumn availability;
    @FXML
    private TableColumn driver;


    public void confirm(ActionEvent event) {
        if(!validator.validateTextFields(Arrays.asList(licencefill))){
            return;
        }
       Service<String> service = getRequest("/vehicles/create?licence="+licencefill);
        service.setOnSucceeded((WorkerStateEvent e) -> {
            displayError("Vehicle created successfully");
        });
    }

    public void clear(ActionEvent event) {
        licencefill.setText("");
        try {
            driverCombo.valueProperty().setValue(null);
        }catch (Exception e){/*nothing to worry about just not filled*/}
    }

    @FXML
    public void initialize() {
        if(init==true){
            restart();
            return;
        }
        initCombo(driverCombo,"/regularuser/name/truckless");
        intitVehicles();
        init=true;
    }


    private void intitVehicles() {
        setRowFact();
        Service<String> service = getInitRequest("/vehicle-driver");
        service.setOnSucceeded((WorkerStateEvent event) -> {
            tableView.getItems().clear();
            licencePlate.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("licencePlate"));
            availability.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("available"));
            driver.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("driver"));
            tableView.getItems().addAll(EntityFactory.parseVehicle(parse(service.getValue())));
            initSynchronizers.add(setTimeout(60,service));
        });
    }


    private void setRowFact() {
        tableView.setRowFactory(tv -> {
            TableRow<Message> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                   // Message rowData = row.getItem();
                    //stageManager.getSession().setViewingMessage(rowData);
                   // switchSceneEvent(FxmlView.MSGVIEW);
                }
            });
            return row ;
        });
    }
}
