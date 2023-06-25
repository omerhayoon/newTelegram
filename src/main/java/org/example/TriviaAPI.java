package org.example;

public class TriviaAPI {
    private static final String REQUEST_ADRESS = "https://opentdb.com/api.php?amount=1";
    private static final String JSON_OBJECT_STRING = "question";


    public static String TriviaAPI(){
        return ApiPath.generateTrivia(REQUEST_ADRESS, JSON_OBJECT_STRING);
    }

}
