package cms.client.http.requests;

import org.apache.log4j.Logger;

import java.net.HttpURLConnection;
import java.net.URL;

public abstract class HttpRequest {
    protected final static Logger LOG = Logger.getLogger(HttpRequest.class);
    protected HttpURLConnection con;
      protected URL obj;

    public HttpURLConnection getCon() {
        return con;
    }

    public URL getUrl() {
        return obj;
    }


}
