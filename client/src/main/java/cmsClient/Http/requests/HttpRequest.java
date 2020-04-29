package cmsClient.Http.requests;

import java.net.HttpURLConnection;
import java.net.URL;

public abstract class HttpRequest {
      HttpURLConnection con;
      protected URL obj;

    public HttpURLConnection getCon() {
        return con;
    }

    public URL getUrl() {
        return obj;
    }


}
