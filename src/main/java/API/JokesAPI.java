package API;


import API.ApiPath;

public class JokesAPI {
    private static final String REQUEST_ADRESS = "https://v2.jokeapi.dev/joke/any";
    private static final String JSON_OBJECT_STRING = "setup";


    public static String jokeAPI() {
        return ApiPath.generateAPI(REQUEST_ADRESS,JSON_OBJECT_STRING);
    }


}

