package org.example;

public class QuoteAPI {

    private static final String REQUEST_ADRESS = "https://api.quotable.io/quotes/random";
    private static final String JSON_OBJECT_STRING = "content";

    public static String ipAPI(){
        return ApiPath.generateAPI(REQUEST_ADRESS, JSON_OBJECT_STRING);
    }
}
