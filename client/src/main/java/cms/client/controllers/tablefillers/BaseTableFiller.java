package cms.client.controllers.tablefillers;

import javafx.concurrent.Service;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public abstract class BaseTableFiller<T> {

    abstract ArrayList<Service<String>> initServices();
    abstract void initTable(TableView<T> table);
}
