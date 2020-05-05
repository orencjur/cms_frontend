package cms.client.controllers;

import cms.client.controllers.entityhelpers.Message;
import cms.client.view.FxmlView;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class MsgBoardController extends AbstractController {

    @FXML private TableView<Message> tableView;
    @FXML private TableColumn<Message, String> user;
    @FXML private TableColumn<Message, String> date;
    @FXML private TableColumn<Message, String> content;
    @FXML private TableColumn<Message, String> time;


    @FXML
    public void initialize() {
        if(init==true){
            restart();
            return;
        }
        setRowFact();
        Service<String> service = getRequest("/messages?user="+stageManager.getSession().getLoggedUser()+"&role="+stageManager.getSession().getLoggedRole());
        service.setOnSucceeded((WorkerStateEvent event) -> {
            tableView.getItems().clear();
            user.setCellValueFactory(new PropertyValueFactory<Message, String>("user"));
            date.setCellValueFactory(new PropertyValueFactory<Message, String>("date"));
            time.setCellValueFactory(new PropertyValueFactory<Message, String>("time"));
            content.setCellValueFactory(new PropertyValueFactory<Message, String>("content"));
            tableView.getItems().addAll(parseMessages(parse(service.getValue())));
            initSynchronizers.add(setTimeout(60,service));
        });

        init=true;
    }

    private List<Message> parseMessages(List<String> strings){

        List<Message> result = new ArrayList<Message>();
        if(strings.size()%3!=0){
            LOG.debug("bad parsing");
            return result;
        }
        for(int i=0;i<strings.size();i+=3){
            result.add(new Message(strings.get(i),strings.get(i+1),strings.get(i+2)));
        }
        return result;
    }

    private void setRowFact(){
        tableView.setRowFactory( tv -> {
            TableRow<Message> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Message rowData = row.getItem();
                    stageManager.getSession().setViewingMessage(rowData);
                    switchSceneEvent(FxmlView.MSGVIEW);
                }
            });
            return row ;
        });
    }

    public void newMessage(ActionEvent event) {
        switchSceneEvent(FxmlView.NEW);
    }


}
