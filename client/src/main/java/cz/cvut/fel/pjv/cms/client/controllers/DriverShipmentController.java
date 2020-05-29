package cz.cvut.fel.pjv.cms.client.controllers;

import cz.cvut.fel.pjv.cms.client.controllers.entityhelpers.EntityFactory;
import cz.cvut.fel.pjv.cms.client.controllers.entityhelpers.Shipment;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class DriverShipmentController extends AbstractController {
    @FXML
    private TableView shipmentTable1;
    @FXML
    private TableColumn expedition1;
    @FXML
    private TableColumn status1;
    @FXML
    private TableColumn cargo1;
    @FXML
    private TableColumn destination1;
    @FXML
    private TableColumn expedition;
    @FXML
    private TableColumn status;

    @FXML
    private TableColumn destination;
    @FXML
    private TableColumn cargo;


    @FXML
    private TableView<Shipment> shipmentTable;


    @FXML
    public void initialize() {

        intitStatuses();
    }




    private void intitStatuses() {
        ArrayList<Service<String>> statusServices = initStatusServices();

    }

    private ArrayList<Service<String>> initStatusServices(){
        ArrayList<Service<String>> statusServices = new ArrayList<>();
        statusServices.add(getInitRequest("/inactiveShipment/driver?driver="+stageManager.getSession().getLoggedUser()));
        statusServices.add(getInitRequest("/activeshipment/driver?driver="+stageManager.getSession().getLoggedUser()));
        setSucceededStatusService1(statusServices.get(0));
        setSucceededStatusService(statusServices.get(1));
        return statusServices;
    }

    private void setSucceededStatusService(Service<String> service){
        setSucceededStatusService(service, shipmentTable, expedition, status, cargo, destination);
    }

    private void setSucceededStatusService(Service<String> service, TableView<Shipment> shipmentTable, TableColumn expedition, TableColumn status, TableColumn cargo, TableColumn destination) {
        service.setOnSucceeded((WorkerStateEvent event) -> {
            httpErrorWindow(service);
            shipmentTable.getItems().clear();
            expedition.setCellValueFactory(new PropertyValueFactory<Shipment, String>("expedion"));
            status.setCellValueFactory(new PropertyValueFactory<Shipment, String>("status"));
            cargo.setCellValueFactory(new PropertyValueFactory<Shipment, String>("vehicle"));
            destination.setCellValueFactory(new PropertyValueFactory<Shipment, String>("destination"));
            shipmentTable.getItems().addAll(EntityFactory.parseShipment(parse(service.getValue())));
            initSynchronizers.add(setTimeout(60,service));
        });
    }

    private void setSucceededStatusService1(Service<String> service){
        setSucceededStatusService(service, shipmentTable1, expedition1, status1, cargo1, destination1);
    }


    public void finished(ActionEvent event) {
       Service<String> service =  getRequest("/finished?driver="+stageManager.getSession().getLoggedUser());
        service.setOnSucceeded((WorkerStateEvent e) -> {
            initialize();
        });
    }
}
