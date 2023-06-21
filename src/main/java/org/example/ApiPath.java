package org.example;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiPath {

    public static String generateAPI(String requestAddress, String line) {
        String joke = "";
        try {
            GetRequest getRequest = Unirest.get(requestAddress);
            HttpResponse<String> response = getRequest.asString();
            if(response.getStatus() == 200 || response.getStatus() == 201){
                String json = response.getBody();
                joke = parseJokeFromJson(json,line);
                System.out.println(joke);
            }
        } catch (Exception e) {

        }
        return joke;

    }
    private static String parseJokeFromJson(String jsonResponse,String line) {
        String joke = "";
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            try {
                joke = jsonObject.getString(line);
            } catch (Exception e) {

            }
            return joke;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String emptyAPI(String REQUEST_ADRESS) {
        String fact = "";
        try {
            GetRequest getRequest = Unirest.get(REQUEST_ADRESS);
            HttpResponse<String> response = getRequest.asString();
            if(response.getStatus() == 200 || response.getStatus() == 201){
                fact = response.getBody();
                System.out.println(fact);
            }
        } catch (Exception e) {

        }
        return fact;
    }
}
