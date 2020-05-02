package cmsClient.Http;

import cmsClient.FxmlHandler.StageManager;
import cmsClient.Http.requests.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import cmsClient.controllers.AbstractController;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.apache.log4j.Logger;

import static java.lang.Thread.*;
import static java.lang.Thread.sleep;

public class HtttpHandler extends Service<String> {

    String baseurl ="http://localhost:8080/cms";
    private final static Logger LOG = Logger.getLogger(HtttpHandler.class);
    private HttpRequestFactory factory =new HttpRequestFactory(baseurl);
    private String url;
    private final StageManager stageManager = StageManager.getInstance();

    public HtttpHandler(String url) {
        this.url = url;
    }

    public void createFactory(){
        factory = new HttpRequestFactory(baseurl);
    }

    private String sendGetRequest(String url) {
        HttpRequest request = null;
        try {
            request = factory.createGetRequest(url);
            LOG.debug("http request: " + request.getCon());
            return getResponse(request);

        } catch (IOException e) {
            LOG.debug("no connecntio trying again");
            return "noconnection";
        }
    }


    private String getResponse (HttpRequest request) throws IOException {
        HttpURLConnection con = request.getCon();
        LOG.info("Httt response code: "+con.getResponseCode());
        LOG.debug("Server answered"+con.getResponseMessage());

        if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
            return readMessage(con);
        }
        con.disconnect();
        //throw new HttpException(con.getResponseCode())
        return String.valueOf(con.getResponseCode());
    }

    private String readMessage (HttpURLConnection con) throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            LOG.debug("response: "+response.toString());
            return response.toString();
    }


    @Override
    protected Task createTask() {
        sendGetRequest(url);
        return new Task<String>() {
            @Override protected String call() throws InterruptedException {
                return sendGetRequest(url);

            }
        };
    }
}
