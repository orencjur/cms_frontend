package cmsClient.Http.requests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetHttp extends HttpRequest {

    public GetHttp(String url) throws IOException {

        obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    }
}
