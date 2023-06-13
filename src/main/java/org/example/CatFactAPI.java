package org.example;

public class CatFactAPI {
    private static final String REQUEST_ADRESS = "https://catfact.ninja/fact?max_length=140";
    private static final String JSON_OBJECT_STRING = "fact";

    public static String catFactAPI(){
       return ApiPath.catFact(REQUEST_ADRESS, JSON_OBJECT_STRING);
    }
}
