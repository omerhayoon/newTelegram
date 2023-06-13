package org.example;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class JokesAPI {
    public JokesAPI( ) {
        returnJoke();
    }
    public static String returnJoke() {
        Random random = new Random();
        String[] jokes = {"Christmas", "Spooky", "Pun", "Dark", "Programming", "Misc", "Any"};
        String apiUrl = "https://v2.jokeapi.dev/joke/" + jokes[random.nextInt(jokes.length)];
        String joke = "";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                String jsonResponse = response.toString();
                joke = parseJokeFromJson(jsonResponse);
                System.out.println(joke); // or store it in a variable or use it as needed
            } else {
                System.out.println("Failed to fetch joke. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return joke;

    }

    private static String parseJokeFromJson(String jsonResponse) {
        String joke = "";
        try {
            // Parse the JSON response
            JSONObject jsonObject = new JSONObject(jsonResponse);
            try {
                joke = jsonObject.getString("joke");
            } catch (Exception e) {

            }
            try {
                joke = jsonObject.getString("setup");
                joke += " " + jsonObject.getString("delivery");

            } catch (Exception e) {
                System.out.println("Setup worked");
            }
            return joke;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}

