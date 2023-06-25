package API;

import API.ApiPath;

public class NumbersFactAPI {
    private static final String REQUEST_ADRESS = "http://numbersapi.com/random";


    public static String NumbersFactAPI() {
        return ApiPath.emptyAPI(REQUEST_ADRESS);
    }

}
