package org.example;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiPath {
    public static String catFact(String requestAdress, String fact) {
        String joke = "";
        try {
            GetRequest getRequest = Unirest.get(requestAdress);
            HttpResponse<String> response = getRequest.asString();
            if(response.getStatus() == 200 || response.getStatus() == 201){
                String json = response.getBody();
                joke = parseJokeFromJson(json,fact);
                System.out.println(joke);
            }
        } catch (Exception e) {

        }
        return joke;

    }
    private static String parseJokeFromJson(String jsonResponse,String fact) {
        String joke = "";
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            try {
                joke = jsonObject.getString(fact);
            } catch (Exception e) {
                System.out.println("Cat fact doesn't work");
            }
            return joke;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
