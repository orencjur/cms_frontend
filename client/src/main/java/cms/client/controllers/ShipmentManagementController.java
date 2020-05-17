package cms.client.controllers;

import cms.client.controllers.entityhelpers.EntityFactory;
import cms.client.controllers.entityhelpers.Shipment;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShipmentManagementController extends AbstractController {
    @FXML
    private TableColumn expedition;
    @FXML
    private TableColumn status;
    @FXML
    private TableColumn completition;
    @FXML
    private TableColumn shipmentVehicle;
    @FXML
    private TableColumn driver;
    @FXML
    private TableColumn destination;
    @FXML
    private TextField cargo;

    @FXML
    private DatePicker date;

    @FXML
    private TextField country;

    @FXML
    private TextField city;

    @FXML
    private TextField adress;

    @FXML
    private ComboBox<String> vehicle;

    @FXML
    private ComboBox<String> statuses;

    @FXML
    private TableView<Shipment> shipmentTable;


    @FXML
    public void initialize() {
        if(init==true){
            restart();
            return;
        }
        intitStatuses();
        initVehicleCombo(vehicle,"/vehicles");
        init=true;
    }


    public void clear(ActionEvent event) {
        cargo.clear();
        date.getEditor().clear();
        country.clear();
        city.clear();
        adress.clear();
        try {
            vehicle.valueProperty().setValue(null);
        }catch (Exception e){/*nothing to worry about just not filled*/}
    }

    public void confirm(ActionEvent event) {
        if(cargo.getText().trim().equals("") || country.getText().trim().equals("") || city.getText().trim().equals("") || adress.getText().trim().equals("") || vehicle.getSelectionModel().isEmpty() || date.getValue()==null) {
            System.out.println("kokot");
            displayError("Please fill every field");
        }
        else if(date.getValue().atStartOfDay().compareTo(LocalDate.now().atStartOfDay())<0){
            LOG.debug("nobody can change their past");
            displayError("Please enter a valid date");
        }
        else {
            String url ="/createshipment?cargo="+cargo.getText()+"&vehicle="+vehicle.getValue()+"&date="+date.getValue()+"&destination="+country.getText()+city.getText()+adress.getText();
            Service<String> service = getRequest(url);
            service.setOnSucceeded(e -> {
                LOG.debug("shipment created");
            });
        }
    }
    private void intitStatuses() {
        ArrayList<Service<String>> statusServices = initStatusServices();
        statuses.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                initSynchronizers.remove(statusServices);
                if (statuses.getValue().equals("Active")) {
                    statusServices.get(0).restart();
                } else if(statuses.getValue().equals("Inactive")){
                    statusServices.get(1).restart();
                }else if(statuses.getValue().equals("Both")){
                    statusServices.get(2).restart();
            }
            }
        });
        statuses.setValue("Both");
    }

    private ArrayList<Service<String>> initStatusServices(){
        ArrayList<Service<String>> statusServices = new ArrayList<>();
        statusServices.add(getRequest("/activeshipment"));
        statusServices.add(getRequest("/inactiveshipment"));
        statusServices.add(getRequest("/shipment"));
        for(Service s : statusServices){
            setSucceededStatusService(s);
        }
        return statusServices;
    }

    private void setSucceededStatusService(Service<String> service){
        service.setOnSucceeded((WorkerStateEvent event) -> {
            shipmentTable.getItems().clear();
            expedition.setCellValueFactory(new PropertyValueFactory<Shipment, String>("expedion"));
            status.setCellValueFactory(new PropertyValueFactory<Shipment, String>("status"));
            completition.setCellValueFactory(new PropertyValueFactory<Shipment, String>("completion"));
            shipmentVehicle.setCellValueFactory(new PropertyValueFactory<Shipment, String>("vehicle"));
            driver.setCellValueFactory(new PropertyValueFactory<Shipment, String>("driver"));
            destination.setCellValueFactory(new PropertyValueFactory<Shipment, String>("destination"));
            shipmentTable.getItems().addAll(EntityFactory.parseShipment(parse(service.getValue())));
            initSynchronizers.add(setTimeout(60,service));
        });
    }



}
