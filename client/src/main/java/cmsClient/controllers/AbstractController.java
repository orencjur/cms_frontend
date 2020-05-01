package cmsClient.controllers;

import cmsClient.FxmlHandler.StageManager;
import cmsClient.Http.HttpRequestFactory;
import cmsClient.Http.HtttpHandler;

import java.io.IOException;

public abstract class  AbstractController {
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
}
