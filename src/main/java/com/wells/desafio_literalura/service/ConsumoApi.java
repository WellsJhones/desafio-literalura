package com.wells.desafio_literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    private String endereco = "https://www.googleapis.com/books/v1/volumes?q=";
    private String launguage = "&langRestrict=";
    private String busca = "/?search=";
    private final String endUrl = "&maxResults=1";

    public String obterDados(String titulo) throws JsonProcessingException {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco + busca + titulo.toLowerCase() + endUrl))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.body();

    }

}
