package cmsClient.controllers;

import cmsClient.Http.HttpRequestFactory;

public class  AbstractController {
     HttpRequestFactory factory;

    AbstractController(String baseurl){
        factory = new HttpRequestFactory(baseurl);
    }
}
