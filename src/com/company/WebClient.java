package com.company;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebClient {

    public static HttpResponse post(String data) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;

        //System.out.println(data);

        request = HttpRequest.newBuilder()
                .uri(URI.create("http://hoiu3.crtech.space/?id=HOIU3"))
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        HttpResponse response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return response;
    }

    public static HttpResponse get() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://hoiu3.crtech.space/?id=HOIU3")).GET().build();

        HttpResponse response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(response.body());

        return response;
    }

}
