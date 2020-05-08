package cms.client.controllers.TableFillers;

import cms.client.async.TimeoutSericeSynchronizer;
import cms.client.controllers.entityhelpers.Shipment;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.HashSet;

public class ShipmentFiller {//extends BaseTableFiller implements TableFillerInteface {
    /*@FXML
    TableView<Shipment> table;

  //  @Override
    public void initTable(TableView<Shipment> table, HashSet<TimeoutSericeSynchronizer> initSynchronizers, ArrayList<TableColumn<Shipment,String>> colums) {
        ArrayList<Service<String>> statusServices = initServices();
        this.table.v.addListener(new ChangeListener<String>() {
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

    @Override
    protected ArrayList<Service<String>> initServices() {
        return null;
    }


    @Override
    void initTable(TableView table) {

    }

*/
}
