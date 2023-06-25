package API;

import API.ApiPath;

public class ipAPI {

    private static final String REQUEST_ADRESS = "https://api.ipify.org/?format=json";
    private static final String JSON_OBJECT_STRING = "ip";

    public static String ipAPI(){
        return ApiPath.generateAPI(REQUEST_ADRESS, JSON_OBJECT_STRING);
    }
}
