package cmsClient.controllers;

import cmsClient.Client;
import cmsClient.FxmlHandler.StageManager;
import cmsClient.Http.HttpRequestFactory;
import cmsClient.Http.HtttpHandler;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class  AbstractController {

    protected final static Logger LOG = Logger.getLogger(AbstractController.class);
    private final HtttpHandler htttpHandler = HtttpHandler.getInstance();
    protected final StageManager stageManager = StageManager.getInstance();

    protected String getRequest(String url)  {
        try {
            return htttpHandler.sendGetRequest(url);
        }catch (IOException e){
            //vokno cant connect to server, maybe try send again?
            return "";
        }
    }
    private void HttpErrorWindow(String response){

    }
    protected static List<String> parse(String toparse){
        return  Arrays.asList(toparse.split("@",0));
    }
}
