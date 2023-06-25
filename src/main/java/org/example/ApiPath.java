package org.example;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiPath {

    public static String generateAPI(String requestAddress, String line) {
        String joke = "";
        try {
            GetRequest getRequest = Unirest.get(requestAddress);
            HttpResponse<String> response = getRequest.asString();
            if (response.getStatus() == 200 || response.getStatus() == 201) {
                String json = response.getBody();
                joke = parseFromJson(json, line);
                System.out.println(joke);
            }
        } catch (Exception e) {

        }
        return joke;

    }

    private static String parseFromJson(String jsonResponse, String line) {
        String temp = "";

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            if (line.equals("setup")) {
                try {
                    temp = jsonObject.getString("joke");
                } catch (Exception e) {

                }
                try {
                    temp = jsonObject.getString("setup");
                    temp += " " + jsonObject.getString("delivery");
                } catch (Exception e) {

                }
            } else {
                try {
                    temp = jsonObject.getString(line);
                } catch (Exception e) {

                }
            }

            return temp;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String generateTrivia(String requestAddress, String line) {
        String joke = "";
        try {
            GetRequest getRequest = Unirest.get(requestAddress);
            HttpResponse<String> response = getRequest.asString();
            if (response.getStatus() == 200 || response.getStatus() == 201) {
                String json = response.getBody();
                joke = parseTrivia(json, line);
                System.out.println(joke);
            }
        } catch (Exception e) {

        }
        return joke;

    }

    private static String parseTrivia(String jsonResponse, String line) {
        String temp = "";
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray results = jsonObject.getJSONArray("results");
            if (results.length() > 0) {
                JSONObject triviaObject = results.getJSONObject(0);
                if (line.equals("question")) {
                    temp = triviaObject.getString("question") + "\n";
                    temp += "The answer is: \n";
                    temp += triviaObject.getString("correct_answer");
                } else {
                    System.out.println("No trivia question found in the response.");
                }
                return temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String emptyAPI(String REQUEST_ADDRESS) {
        String tempString = "";
        try {
            GetRequest getRequest = Unirest.get(REQUEST_ADDRESS);
            HttpResponse<String> response = getRequest.asString();
            if (response.getStatus() == 200 || response.getStatus() == 201) {
                tempString = response.getBody();
                System.out.println(tempString);
            }
        } catch (Exception e) {

        }
        return tempString;
    }
}
