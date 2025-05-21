package com.challenge.conversorapp;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
public class ConsultaDivisa {

    public ConsultaDivisa() {

    }

    String api = "https://v6.exchangerate-api.com/v6/10020a4d1bc7e8dd719a0aef/latest/";

    public float[] consultarDivisa (String divisa1, String divisa2) throws IOException, InterruptedException {
        float valorD1;
        float valorD2;
        float valores [] = new float[2];
        String direccion = api+divisa1;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();
        HttpResponse<String> response = client
                .send(request,HttpResponse.BodyHandlers.ofString());

        //System.out.println(response.body());

        Gson gson = new Gson();
        JsonObject json = gson.fromJson(response.body(), JsonObject.class);

        //String baseCode = json.get("base_code").getAsString();
        JsonObject rates = json.getAsJsonObject("conversion_rates");
        valorD1 = rates.get(divisa1).getAsFloat();
        valorD2 = rates.get(divisa2).getAsFloat();
        valores[0]=valorD1;
        valores[1]=valorD2;

        //String mensaje ="Base de conversión " + baseCode+ " 1 "+divisa1+" equivale a "+valorD2+" "+ divisa2;
        return valores;
    }

}
