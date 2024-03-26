package org.launchcode.growsphere.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

    @Service
    public class WeatherService {


        private static final String API_KEY = "d43c36d1b3mshc382e92ac402c96p153c32jsnbb81e03b8731";
        private static final String API_HOST = "open-weather13.p.rapidapi.com";

        public String getWeatherByCity(String city) {

            try {
                String encodedCity = URLEncoder.encode(city, "UTF-8");
                String url = "https://open-weather13.p.rapidapi.com/city/" + encodedCity;

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("X-RapidAPI-Key", API_KEY)
                        .header("X-RapidAPI-Host", API_HOST)
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();

                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());



                Gson gson = new Gson();
                JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

//            String longitude= jsonResponse.get("coord").get("lon").getAsString();
//            String latitude = jsonResponse.get("lat").getAsString();

                String longitude = String.valueOf(jsonResponse.getAsJsonObject("coord").get("lon").getAsDouble());
                String latitude = String.valueOf(jsonResponse.getAsJsonObject("coord").get("lat").getAsDouble());

                String weatherUrl = UriComponentsBuilder.fromUriString("https://open-weather13.p.rapidapi.com/city/fivedaysforcast/{lat}/{lon}")
                        .buildAndExpand(latitude, longitude)
                        .toUriString();

                HttpRequest weatherRequest = HttpRequest.newBuilder()
                        .uri(URI.create(weatherUrl))
                        .header("X-RapidAPI-Key", API_KEY)
                        .header("X-RapidAPI-Host", API_HOST)
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();

                HttpResponse<String> weatherResponse = HttpClient.newHttpClient()
                        .send(weatherRequest, HttpResponse.BodyHandlers.ofString());

                return weatherResponse.body();

            } catch (UnsupportedEncodingException e) {
                System.err.println("Unsupported encoding: " + e.getMessage());
                return "Error occurred: Unsupported encoding";
            } catch (IOException e) {
                System.err.println("IO error occurred: " + e.getMessage());
                return "Error occurred: IO error";
            } catch (InterruptedException e) {
                System.err.println("Interrupted error occurred: " + e.getMessage());
                return "Error occurred: Interrupted error";
            } catch (Exception e) {
                System.err.println("Unexpected error occurred: " + e.getMessage());
                return "Error occurred: " + e.getMessage();
            }


        }

}
