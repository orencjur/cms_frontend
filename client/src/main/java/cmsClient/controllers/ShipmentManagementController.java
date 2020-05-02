package cmsClient.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

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
        getRequest("/vehicles");
        //LOG.debug(response+"\n"+parse(response));
        //vehicle.getItems().addAll(parse(response));
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
        if(cargo.getText().trim().equals("") || country.getText().trim().equals("") || city.getText().trim().equals("") || adress.getText().trim().equals("") || vehicle.getSelectionModel().isEmpty() || date.getValue()==null){
            System.out.println("kokot");
            //vokno please fill everything
        }
    }
}
