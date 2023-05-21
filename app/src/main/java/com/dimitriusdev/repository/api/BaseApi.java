package com.dimitriusdev.repository.api;

public class BaseApi {

    //    IP
    //    HOME:     http://192.168.100.7:9090/
    //    SERVER:   http://45.143.137.96:9090/

    private final String BASE_URL = "http://192.168.100.7:9090";

    String getServiceUri(){
        return BASE_URL;
    }

}
