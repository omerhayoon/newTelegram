package org.example;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;

public class NumbersFactAPI {
    private static final String REQUEST_ADRESS = "http://numbersapi.com/random";


    public static String NumbersFactAPI() {
        return ApiPath.emptyAPI(REQUEST_ADRESS);
    }

}
