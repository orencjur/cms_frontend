package cms.client.controllers;

import cms.client.controllers.entityhelpers.EntityFactory;
import cms.client.controllers.entityhelpers.Message;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class VehicleManagementController extends AbstractController {
    @FXML
    private TableView tableView;

    @FXML
    private TableColumn licencePlate;
    @FXML
    private TableColumn availability;
    @FXML
    private TableColumn driver;

    public void confirm(ActionEvent event) {
    }

    public void clear(ActionEvent event) {
    }

    @FXML
    public void initialize() {
        if(init==true){
            restart();
            return;
        }
        intitVehicles();
        init=true;
    }

    private void intitVehicles() {
        setRowFact();
        Service<String> service = getInitRequest("/vehicles");
        service.setOnSucceeded((WorkerStateEvent event) -> {
            tableView.getItems().clear();
            licencePlate.setCellValueFactory(new PropertyValueFactory<Message, String>("user"));
            availability.setCellValueFactory(new PropertyValueFactory<Message, String>("date"));
            driver.setCellValueFactory(new PropertyValueFactory<Message, String>("time"));
            tableView.getItems().addAll(EntityFactory.parseMessages(parse(service.getValue())));
            initSynchronizers.add(setTimeout(60,service));
        });
    }


    private void setRowFact() {
        tableView.setRowFactory(tv -> {
            TableRow<Message> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Message rowData = row.getItem();
                    stageManager.getSession().setViewingMessage(rowData);
                   // switchSceneEvent(FxmlView.MSGVIEW);
                }
            });
            return row ;
        });
    }
}
