package cz.cvut.fel.pjv.cms.client.controllers;

import cz.cvut.fel.pjv.cms.client.controllers.entityhelpers.EntityFactory;
import cz.cvut.fel.pjv.cms.client.controllers.entityhelpers.Message;
import cz.cvut.fel.pjv.cms.client.view.FxmlView;
import com.jfoenix.controls.JFXButton;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MsgBoardController extends AbstractController {

    @FXML
    private JFXButton old;
    @FXML
    private TableView<Message> tableView;
    @FXML
    private TableColumn<Message, String> user;
    @FXML
    private TableColumn<Message, String> date;
    @FXML
    private TableColumn<Message, String> content;
    @FXML
    private TableColumn<Message, String> time;


    @FXML
    public void initialize() {

        setRowFact();
        if (stageManager.getSession().getLoggedRole() == FxmlView.DRIVER) {
            old.setVisible(false);
            old.setDisable(true);
        }
        Service<String> service = getInitRequest("/messages?user=" + stageManager.getSession().getLoggedUser() + "&role=" + stageManager.getSession().getLoggedRole());
        service.setOnSucceeded((WorkerStateEvent event) -> {
            httpErrorWindow(service);
            tableView.getItems().clear();
            user.setCellValueFactory(new PropertyValueFactory<Message, String>("user"));
            date.setCellValueFactory(new PropertyValueFactory<Message, String>("date"));
            time.setCellValueFactory(new PropertyValueFactory<Message, String>("time"));
            content.setCellValueFactory(new PropertyValueFactory<Message, String>("content"));
            tableView.getItems().addAll(EntityFactory.parseMessages(parse(service.getValue())));
            initSynchronizers.add(setTimeout(60, service));
        });

    }

    private void setRowFact() {
        tableView.setRowFactory(tv -> {
            TableRow<Message> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Message rowData = row.getItem();
                    stageManager.getSession().setViewingMessage(rowData);
                    switchSceneEvent(FxmlView.MSGVIEW);
                }
            });
            return row;
        });
    }

    public void newMessage(ActionEvent event) {
        switchSceneEvent(FxmlView.NEW);
    }

    public void deleteOld(ActionEvent event) {
        Service<String> service = getRequest("/message/deleteold?user=" + stageManager.getSession().getLoggedUser());
        service.setOnSucceeded((WorkerStateEvent e) -> {
            initialize();
        });
    }


}
