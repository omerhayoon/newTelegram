package API;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiPath {
    private static final int WORKING_STATUS = 200;


    public static String generateAPI(String requestAddress, String line) {
        String newString = "";
        try {
            GetRequest getRequest = Unirest.get(requestAddress);
            HttpResponse<String> response = getRequest.asString();
            if (response.getStatus() == WORKING_STATUS || response.getStatus() == WORKING_STATUS + 1) {
                String json = response.getBody();
                newString = parseFromJson(json, line);
                System.out.println(newString);
            }
        } catch (Exception e) {

        }
        return newString;

    }

    private static String parseFromJson(String jsonResponse, String line) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            if (line.equals("setup")) {
                if (jsonObject.has("joke")) {
                    return jsonObject.getString("joke");
                } else if (jsonObject.has("setup")) {
                    String temp = jsonObject.getString("setup");
                    temp += " " + jsonObject.getString("delivery");
                    return temp;
                }
            } else if (jsonObject.has(line)) {
                return jsonObject.getString(line);
            }
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
            if (response.getStatus() == WORKING_STATUS || response.getStatus() == WORKING_STATUS + 1) {
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
            if (response.getStatus() == WORKING_STATUS || response.getStatus() == WORKING_STATUS + 1) {
                tempString = response.getBody();
                System.out.println(tempString);
            }
        } catch (Exception e) {

        }
        return tempString;
    }
}
