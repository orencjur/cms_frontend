package cz.cvut.fel.pjv.cms.client.controllers;

import cz.cvut.fel.pjv.cms.client.controllers.entityhelpers.EntityFactory;
import cz.cvut.fel.pjv.cms.client.controllers.entityhelpers.User;
import cz.cvut.fel.pjv.cms.client.http.HtttpService;
import cz.cvut.fel.pjv.cms.client.view.FxmlView;
import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class UserManagementController extends AbstractController {
    @FXML
    private JFXButton newUser;
    @FXML
    private ComboBox userRole;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn surname;
    @FXML
    private TableColumn username;
    @FXML
    private TableColumn userVehicle;
    @FXML
    private TableColumn licenceNumber;
    @FXML
    private TableColumn available;
    @FXML
    private TableView<User> userTable;


    @FXML
    public void initialize() {

        intitUsers();
    }

    private void intitUsers() {
        setRowFact();

        ArrayList<Service<String>> userServices = initServices();
        userRole.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                shutdown();
                if (userRole.getValue().equals("Dispatcher")) {
                    stageManager.getSession().setCreatingDriver(false);
                    newUser.setText("Create Dispatcher");
                    licenceNumber.setVisible(false);
                    available.setVisible(false);
                    userVehicle.setText("Role");
                    initServices().get(0).restart();
                } else {
                    stageManager.getSession().setCreatingDriver(true);
                    newUser.setText("Create Driver");
                    licenceNumber.setVisible(true);
                    available.setVisible(true);
                    userVehicle.setText("Vehicle");
                    initServices().get(1).restart();
                }
            }
        });
        userRole.setValue("Driver");
    }

    private ArrayList<Service<String>> initServices() {
        ArrayList<Service<String>> statusServices = new ArrayList<>();
        statusServices.add(new HtttpService("/manager/full"));
        statusServices.add(new HtttpService("/regularuser/full"));
        for (Service s : statusServices) {
            setSucceededStatusService(s);
        }
        return statusServices;
    }

    private void setSucceededStatusService(Service<String> service) {
        service.setOnSucceeded((WorkerStateEvent event) -> {
            httpErrorWindow(service);
            userTable.getItems().clear();
            name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
            surname.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));
            username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
            userVehicle.setCellValueFactory(new PropertyValueFactory<User, String>("userVehicle"));
            licenceNumber.setCellValueFactory(new PropertyValueFactory<User, String>("licenceNumber"));
            available.setCellValueFactory(new PropertyValueFactory<User, String>("availability"));
            userTable.getItems().addAll(EntityFactory.parseUser(parse(service.getValue())));
            initSynchronizers.add(setTimeout(60, service));
        });
    }

    private void setRowFact() {
        userTable.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    User rowData = row.getItem();
                    stageManager.getSession().setViewingUser(rowData);
                    switchSceneEvent(FxmlView.MODIFYUSER);
                }
            });
            return row;
        });
    }

    public void newUser(ActionEvent event) {
        switchSceneEvent(FxmlView.NEWUSER);
    }
}
