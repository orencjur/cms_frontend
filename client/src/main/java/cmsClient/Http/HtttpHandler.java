package cmsClient.Http;

import cmsClient.FxmlHandler.StageManager;
import cmsClient.Http.requests.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.apache.log4j.Logger;

public class HtttpHandler {

    private final static Logger LOG = Logger.getLogger(HtttpHandler.class);
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
        LOG.debug("http request: "+request.getCon());
        return getResponse(request);
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
}
