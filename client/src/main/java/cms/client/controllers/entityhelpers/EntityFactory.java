package cms.client.controllers.entityhelpers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public class EntityFactory<T > {
    protected final static Logger LOG = Logger.getLogger(EntityFactory.class);

    public List<T> parseT(List<String> strings){

        List<T> result = new ArrayList<T>();
        if(strings.size()%4!=0){
            LOG.debug("bad parsing");
            return result;
        }
        for(int i=0;i<strings.size();i+=4){
            //result.add( new T(strings.get(i),strings.get(i+1),strings.get(i+2),strings.get(i+3)));
        }
        return result;
    }
    public static List<Message> parseMessages(List<String> strings){

        List<Message> result = new ArrayList<Message>();
        if(strings.size()%4!=0){
            LOG.debug("bad parsing");
            return result;
        }
        for(int i=0;i<strings.size();i+=4){
            result.add( new Message(strings.get(i),strings.get(i+1),strings.get(i+2),strings.get(i+3)));
        }
        return result;
    }


    public static List<Shipment> parseShipment(List<String> strings){

        List<Shipment> result = new ArrayList<>();
        if(strings.size()%6!=0){
            LOG.debug("bad parsing "+strings.size());
            return result;
        }
        for(int i=0;i<strings.size();i+=6){
            result.add(new Shipment(strings.get(i),strings.get(i+1),strings.get(i+2),strings.get(i+3),strings.get(i+4),strings.get(i+5)));
        }
        return result;
    }
}
