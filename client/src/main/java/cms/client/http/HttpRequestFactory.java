package cms.client.http;

import cms.client.http.requests.GetHttp;
import cms.client.http.requests.HttpRequest;

import java.io.IOException;

public class HttpRequestFactory {


    private String baseurl ;

    public HttpRequestFactory(String baseurl){
        this.baseurl = baseurl;
    }

    public HttpRequest createGetRequest(String url) throws IOException {
        return new GetHttp(baseurl+url);
    }
}
