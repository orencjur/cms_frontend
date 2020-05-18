package cms.client.controllers;

import cms.client.async.Timeout;
import cms.client.async.TimeoutSericeSynchronizer;
import cms.client.controllers.entityhelpers.EntityFactory;
import cms.client.controllers.entityhelpers.Shipment;
import cms.client.http.HtttpService;
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
import java.util.Arrays;
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

    private List<TextField> textFields;

    private TimeoutSericeSynchronizer statusSynchronizer;

    @FXML
    public void initialize() {

        intitStatuses();
        initCombo(vehicle,"/regularuser/name/available");
        initTextfields();
    }

    private void initTextfields() {
        textFields = new ArrayList<>();
        textFields.addAll(Arrays.asList(cargo,country,city,adress));
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
       if(!validator.validateTextFields(textFields)){
           return;
       }
       if(vehicle.getValue()==null){
           displayError("Please enter driver");
       }
        if(date.getValue()==null || date.getValue().atStartOfDay().compareTo(LocalDate.now().atStartOfDay())<0){
            LOG.debug("nobody can change their past");
            displayError("Please enter a valid date");
        }
        else {
            String url ="/createshipment?cargo="+cargo.getText()+"&vehicle="+vehicle.getValue()+"&date="+date.getValue()+"&destination="+country.getText()+city.getText()+adress.getText();
            Service<String> service = getRequest(url);
            service.setOnSucceeded(e -> {
                LOG.debug("shipment created");
                displayError("shipment created succesfully");
                initialize();
            });
        }
    }
    private void intitStatuses() {
        ArrayList<Service<String>> statusServices = initStatusServices();
        statuses.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(statusSynchronizer!=null){
                    statusSynchronizer.stop();
                }
                if (statuses.getValue().equals("Active")) {
                    initStatusServices().get(0).restart();
                } else if(statuses.getValue().equals("Inactive")){
                    initStatusServices().get(1).restart();
                }else if(statuses.getValue().equals("Both")){
                    initStatusServices().get(2).restart();
            }
            }
        });
        statuses.setValue("Active");
    }

    private ArrayList<Service<String>> initStatusServices(){
        ArrayList<Service<String>> statusServices = new ArrayList<>();
        statusServices.add(new HtttpService("/activeshipment"));
        statusServices.add(new HtttpService("/inactiveShipment"));
        statusServices.add(new HtttpService("/shipment"));
        for(Service s : statusServices){
            setSucceededStatusService(s);
        }
        return statusServices;
    }

    private void setSucceededStatusService(Service<String> service){
        service.setOnSucceeded((WorkerStateEvent event) -> {
            httpErrorWindow(service);
            shipmentTable.getItems().clear();
            expedition.setCellValueFactory(new PropertyValueFactory<Shipment, String>("expedion"));
            status.setCellValueFactory(new PropertyValueFactory<Shipment, String>("status"));
            //completition.setCellValueFactory(new PropertyValueFactory<Shipment, String>("completion"));
            shipmentVehicle.setCellValueFactory(new PropertyValueFactory<Shipment, String>("vehicle"));
            driver.setCellValueFactory(new PropertyValueFactory<Shipment, String>("driver"));
            destination.setCellValueFactory(new PropertyValueFactory<Shipment, String>("destination"));
            shipmentTable.getItems().addAll(EntityFactory.parseShipment(parse(service.getValue())));
            statusSynchronizer=setTimeout(60,service);
            initSynchronizers.add(statusSynchronizer);
        });
    }



}
