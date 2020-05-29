package cz.cvut.fel.pjv.cms.client.http;

import cz.cvut.fel.pjv.cms.client.http.requests.GetHttp;
import cz.cvut.fel.pjv.cms.client.http.requests.HttpRequest;

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
