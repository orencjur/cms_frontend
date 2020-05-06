package cms.client.controllers;

import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ShipmentManagementController extends AbstractController {

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
    public void initialize() {
        if(init==true){
            restart();
            return;
        }
        Service<String> service = getInitRequest("/vehicles");
        service.setOnSucceeded((WorkerStateEvent event) -> {
            vehicle.getItems().clear();
            vehicle.getItems().addAll(parse(service.getValue()));
            initSynchronizers.add(setTimeout(60,service));
        });
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
            //vokno please fill everythin}
        //}
        //else if(date.getValue().atStartOfDay().compareTo(LocalDate.now().atStartOfDay())){

        //}
        }else {
            String url ="/createshipment?cargo="+cargo.getText()+"&vehicle="+vehicle.getValue()+"&date="+date.getValue()+"&destination="+country.getText()+city.getText()+adress.getText();
            Service<String> service = getRequest(url);
            service.setOnSucceeded(e -> {
                LOG.debug("shipment created");
            });
        }
    }
}
