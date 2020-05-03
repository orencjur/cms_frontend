package cms.client.http.requests;

import org.apache.http.NameValuePair;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PostHttp extends HttpRequest {


    public PostHttp(String url, ArrayList<NameValuePair> paramentr) throws IOException {
        try {
            obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
        }
        catch (MalformedURLException e) {
            LOG.debug("url went wrong",e);
        }
    }
}
