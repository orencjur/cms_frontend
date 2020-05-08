package cms.client.controllers.TableFillers;

import cms.client.http.HtttpService;
import javafx.concurrent.Service;

import java.util.ArrayList;

interface TableFillerInteface {

    void initTable();

    default ArrayList<Service<String>> initServices(ArrayList<String> strings){
        ArrayList<Service<String>> statusServices = new ArrayList<>();
        for (String s : strings ){
            statusServices.add(new HtttpService(s));
        }
        statusServices.add(new HtttpService("/activeshipment"));
        statusServices.add(new HtttpService("/inactiveshipment"));
        statusServices.add(new HtttpService("/shipment"));
        for(Service s : statusServices){
            setSucceededService(s);
        }
        return statusServices;
    }

    void setSucceededService(Service<String> s);


}
