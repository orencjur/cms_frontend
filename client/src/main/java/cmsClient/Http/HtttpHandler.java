package cmsClient.Http;

public class HtttpHandler {

    private HttpRequestFactory factory;
    private static  HtttpHandler instance = new HtttpHandler();
    public static HtttpHandler getInstance(){
        return instance;
    }


}
