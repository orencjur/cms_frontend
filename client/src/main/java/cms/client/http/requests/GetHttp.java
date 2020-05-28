package cms.client.http.requests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Creates GET request
 */
public class GetHttp extends HttpRequest {

    public GetHttp(String url) throws IOException {
        try {
            obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
        }
        catch (MalformedURLException e) {
            LOG.debug("url went wrong",e);
        }
    }
}
