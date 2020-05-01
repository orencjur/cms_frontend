package cmsClient.Http;

import cmsClient.FxmlHandler.StageManager;
import cmsClient.Http.requests.HttpRequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;
import static org.slf4j.LoggerFactory.getLogger;

public class HtttpHandler {

    private static final Logger LOG = getLogger(StageManager.class);
    private HttpRequestFactory factory;
    private static  HtttpHandler instance = new HtttpHandler();
    public static HtttpHandler getInstance(){
        return instance;
    }

    public void createFactory(String baseurl){
        factory = new HttpRequestFactory(baseurl);
    }

    public String sendGetRequest(String url) throws IOException {
        HttpRequest  request =  factory.createGetRequest(url);
        return getResponse(request);
    }

    private String getResponse (HttpRequest request) throws IOException {
        HttpURLConnection con = request.getCon();
        LOG.info("Httt response code: "+con.getResponseCode());
        LOG.debug("Server answered",con.getResponseMessage());
        if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
            return con.getResponseMessage();
        }
        return String.valueOf(con.getResponseCode());
    }




}
